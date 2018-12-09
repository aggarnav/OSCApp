"""Small example OSC server

This program listens to several addresses, and prints some information about
received packets.
"""
import math

from pythonosc import dispatcher
from pythonosc import osc_server
from cms import googleearthplot
import simplekml
import os
import socket

if os.name != "nt":
    import fcntl
    import struct


    def get_interface_ip(ifname):
        s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        return socket.inet_ntoa(fcntl.ioctl(s.fileno(), 0x8915, struct.pack('256s',
                                                                            ifname[:15]))[20:24])


def get_lan_ip():
    ip = socket.gethostbyname(socket.gethostname())
    if ip.startswith("127.") and os.name != "nt":
        interfaces = [
            "eth0",
            "eth1",
            "eth2",
            "wlan0",
            "wlan1",
            "wifi0",
            "ath0",
            "ath1",
            "ppp0",
        ]
        for ifname in interfaces:
            try:
                ip = get_interface_ip(ifname)
                break
            except IOError:
                pass
    return ip


def print_volume_handler(unused_address, args):
    print(str(args))
    args = str(args)
    values = args.split(",")
    if len(values) == 3:
        gep = googleearthplot()
        gep.PlotPoints(values[1], values[2],values[0])
        gep.GenerateKMLFile("sample.kml")
        os.startfile("sample.kml")
    else:
        print("going to a planet")
        if values[0] == "sky":
            kml = simplekml.Kml()
            kml.hint = 'target=sky'
            kml.save("sample.kml")
            os.startfile("sample.kml")
        if values[0] == "earth":
            kml = simplekml.Kml()
            kml.save("sample.kml")
            os.startfile("sample.kml")
        if values[0] == "moon":
            kml = simplekml.Kml()
            kml.hint = 'target=moon'
            kml.save("sample.kml")
            os.startfile("sample.kml")
        if values[0] == "mars":
            kml = simplekml.Kml()
            kml.hint = 'target=mars'
            kml.save("sample.kml")
            os.startfile("sample.kml")


ip = get_lan_ip()

dispatcher = dispatcher.Dispatcher()
dispatcher.map("/filter", print_volume_handler)

server = osc_server.ThreadingOSCUDPServer(
    (ip, 4000), dispatcher)
print("Serving on {}".format(server.server_address))
server.serve_forever()
