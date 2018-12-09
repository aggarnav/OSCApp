the code is located at https://github.com/aggarnav/OSCApp

My code runs on two sides: there is the server side code and then there is the client side code. To reduce the amount of processing ot be done on the client side, the entire kml is created only on the server side and all the client has to do is send a command regarding what type of kml file to create. 
Moreover, the code is dynamic i.e. nothing is hardcode. for eg the ip address of the server is actually figured out each time it is run. So, instead of starting all servers on repsective localhosts, the server starts up on the actual network ip. Thus, allowing the client to send instructions from anywhere
Also, i have included some part of my CMS code which if required would allow us to automatically take inputs from the database and then run a function using it. 

I think that OSC is a great replacement to MIDI. It is fast and dynamic, once a connection has been established, the sender can continously send messages. In fact, i got to know that OSC is so fast, that even speakers and other devices like it use OSC to communicate in real time
Furthermore, considering that osc is a widely used protocol, multiple open soruce projects exist to implement it different languages. For example, my code sends a message suing javaosc and the message is received using python osc 

You can find the uncompressed video at https://drive.google.com/open?id=1jeBzXs6TJl-WobobhZPzIT3R32UjIRrQ

