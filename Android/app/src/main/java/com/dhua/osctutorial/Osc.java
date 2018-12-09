package com.dhua.osctutorial;



import android.util.Log;

import java.lang.reflect.Array;
import java.net.*;
import java.util.*;

import com.illposed.osc.*;

import static android.content.ContentValues.TAG;

public class Osc{


  public static void oscRequest(final java.lang.String myIP, final String type) {

    /* These two variables hold the IP address and port number.
     * You should change them to the appropriate address and port.
     */
    final int myPort=4000;
    // This is used to send messages

      // This thread will contain all the code that pertains to OSC
    Thread oscThread = new Thread() {
      @Override
      public void run() {
        /* The first part of the run() method initializes the OSCPortOut for sending messages.
         *
         * For more advanced apps, where you want to change the address during runtime, you will want
         * to have this section in a different thread, but since we won't be changing addresses here,
         * we only have to initialize the address once.
         */
        OSCPort oscPortOut;
        InetAddress adr;

          try {
          // Connect to some IP address and port
              adr = InetAddress.getByName(myIP);
          oscPortOut = new OSCPortOut(adr, myPort);

        } catch (UnknownHostException e) {
            //handle errors
          return;
        } catch (Exception e) {

            // Error handling for any other errors
          return;
        }


        /* The second part of the run() method loops infinitely and sends messages every 500
         * milliseconds.
         */
          if (oscPortOut != null) {
            // Creating the message

            /* The version of JavaOSC from the Maven Repository is slightly different from the one
             * from the download link on the main website at the time of writing this tutorial.
             *
             * The Maven Repository version (used here), takes a Collection, which is why we need
             * Arrays.asList(thingsToSend).
             *
             * If you're using the downloadable version for some reason, you should switch the
             * commented and uncommented lines for message below
             */
            //OSCMessage message = new OSCMessage("/route", Arrays.asList(thingsToSend));
            OSCMessage message = new OSCMessage("/filter");
            message.addArgument(type);
              Log.d(TAG, "message:"+message.getAddress() + message.getArguments());


            /* NOTE: Since this version of JavaOSC uses Collections, we can actually use ArrayLists,
             * or any other class that implements the Collection interface. The following code is
             * valid for this version.
             *
             * The benefit of using an ArrayList is that you don't have to know how much information
             * you are sending ahead of time. You can add things to the end of an ArrayList, but not
             * to an Array.
             *
             * If you want to use this code with the downloadable version, you should switch the
             * commented and uncommented lines for message2
             */


            try {
              // Send the messages
                ((OSCPortOut) oscPortOut).send(message);
                Log.d(TAG, "run: works");



            } catch (Exception e) {
                Log.d(TAG, "run: Didnt work");
                Log.e(TAG, e.toString());

              // Error handling for some error
            }
          }
        }
    };


    // Start the thread that sends messages
    oscThread.start();
  }



}
