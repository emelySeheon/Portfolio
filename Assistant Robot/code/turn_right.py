#!/usr/bin/env python

import time
import rospy
import math
from geometry_msgs.msg import Twist


if __name__ == '__main__':

    rospy.init_node('turn_left')
    cmd_vel_topic = '/mobile_base/commands/velocity'
    cmd_vel_publisher = rospy.Publisher(cmd_vel_topic, Twist, queue_size=10)

    t_end = time.time() + 60 * 0.004

    while time.time() < t_end:
      cmd =  Twist()
      cmd.angular.x = 0
      cmd.angular.y = 0
      cmd.angular.z = 1
      cmd_vel_publisher.publish(cmd)