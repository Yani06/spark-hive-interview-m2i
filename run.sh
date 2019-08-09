#!/bin/bash

echo "Execute spark job from path:" $1

hdfs dfs -copyFromLocal /tmp/crime.csv /user/spark/

hdfs dfs -copyFromLocal /tmp/offense_codes.csv /user/spark

spark-submit --class MainApp --master yarn $1
