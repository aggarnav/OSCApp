#!/usr/bin/env python
# -*- coding: utf-8 -*-
#
# python library for google earth plot
#
# author: Atsushi Sakai
#
# Copyright (c): 2015 Atsushi Sakai
#
# License : GPL Software License Agreement

import simplekml



class googleearthplot:

    def __init__(self):
        self.kml = simplekml.Kml()


    def PlotPoints(self, lon, lat, label, description="", color="red", labelScale=1, time="", id=""):
        """
        Plot only label
        """
        pnt = self.kml.newpoint(name=label,
                                description=description
                                )
        pnt.coords = [(lon, lat)]
        pnt.style.labelstyle.color = self.GetColorObject(color)
        pnt.style.labelstyle.scale = labelScale
        pnt.timestamp.when = time

        print ("[PlotPoint]" + label + ",lat:" + str(lat) + ",lon:" + str(lon) + ",time" + time)


    def GetColorObject(self, color):
        valiableStr = "simplekml.Color." + color
        colorObj = eval(valiableStr)
        return colorObj

    def GenerateKMLFile(self, filepath="sample.kml"):
        """Generate KML File"""
        self.kml.save(filepath)







