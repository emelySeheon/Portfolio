#!/usr/bin/env python3.11

import os
import io
import numpy as np
from six import BytesIO
from PIL import Image, ImageDraw, ImageFont
from six.moves.urllib.request import urlopen
import subprocess
import tensorflow as tf
import tensorflow_hub as hub

tf.get_logger().setLevel('ERROR')

from object_detection.utils import label_map_util
from object_detection.utils import visualization_utils as viz_utils
from object_detection.utils import ops as utils_ops
import cv2
import time
import PIL.ImageFont

def follow(msg):
    print("5")
    message = msg

    #outputString =ymin:xmin;ymax@xmax#
    pos1 = message.find(':')
    pos2 = message.find(';')
    pos3 = message.find('@')
    pos4 = message.find('#')

    ymin = float(message[2:pos1])
    xmin = float(message[(pos1+1):pos2])
    ymax = float(message[(pos2+1):pos3])
    xmax = float(message[(pos3+1):pos4])
    print("6")
    #determine movement
    area = (ymax-ymin)*(xmax-xmin)

    if xmax <= 0.175:
       print("7")
       subprocess.run(["python", "turn_left.py"])
       print("turn left")
    elif xmin >= 0.825:
      print("8")
      subprocess.run(["python", "turn_right.py"])
      print("turn right")

    if area >= 0.11:
      print("10")
      subprocess.run(["python", "move_backward.py"])
      print("move back")
    elif area <= 0.03:
      print("11")
      subprocess.run(["python", "move_forward.py"])
      print("move forward")

if __name__ == '__main__':
      print("1")
      COCO17_HUMAN_POSE_KEYPOINTS = [(0, 1),
       (0, 2),
       (1, 3),
       (2, 4),
       (0, 5),
       (0, 6),
       (5, 7),
       (7, 9),
       (6, 8),
       (8, 10),
       (5, 6),
       (5, 11),
       (6, 12),
       (11, 12),
       (11, 13),
       (13, 15),
       (12, 14),
       (14, 16)]

      #Path to labels
      PATH_TO_LABELS = './models/research/object_detection/data/mscoco_label_map.pbtxt'
      category_index = label_map_util.create_category_index_from_labelmap(PATH_TO_LABELS, use_display_name=True)

      vid = cv2.VideoCapture(0)
      time.sleep(1)
      ret, frame = vid.read()
      #Convert image to correct format
      image = PIL.Image.fromarray(frame)

      #Model
      model_handle = 'https://tfhub.dev/tensorflow/efficientdet/d0/1'
      hub_model = hub.load(model_handle)

      print("2")

      while (True):
        ret, frame = vid.read()
        print("3")
        #Convert image to correct format
        image = PIL.Image.fromarray(frame)
        (im_width, im_height) = image.size
        image_np = np.array(image.getdata()).reshape((1, im_height, im_width, 3)).astype(np.uint8)

        #Get results
        results = hub_model(image_np)
        result = {key:value.numpy() for key,value in results.items()}

        #Label
        label_id_offset = 0
        image_np_with_detections = image_np.copy()
        keypoints, keypoint_scores = None, None

        if 'detection_keypoints' in result:
          keypoints = result['detection_keypoints'][0]
          keypoint_scores = result['detection_keypoint_scores'][0]

        viz_utils.visualize_boxes_and_labels_on_image_array(
            image_np_with_detections[0],
            result['detection_boxes'][0],
            (result['detection_classes'][0] + label_id_offset).astype(int),
            result['detection_scores'][0],
            category_index,
            use_normalized_coordinates=True,
            max_boxes_to_draw=200,
            min_score_thresh=.30,
            agnostic_mode=False,
            keypoints=keypoints,
            keypoint_scores=keypoint_scores,
            keypoint_edges=COCO17_HUMAN_POSE_KEYPOINTS)

        #Output results
        for i in range(result['detection_boxes'][0].shape[0]):
            if result['detection_scores'][0] is None or result['detection_scores'][0][i] > .30:
                  box = tuple(result['detection_boxes'][0][i].tolist())
                  if category_index[(result['detection_classes'][0] + label_id_offset).astype(int)[i]]['name'] == "person":
                        ymin, xmin, ymax, xmax = box
                        outputString = str(ymin) + ":" + str(xmin) + ";" + str(ymax) + "@" + str(xmax) + "#"
                        print("4")
                        follow(outputString)
                        print("5")
        #Quit option
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break

      #Cleanup
      vid.release()
      cv2.destroyAllWindows()