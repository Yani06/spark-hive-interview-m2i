**Pre-requisite** 

Copy crime.csv from **_src/main/resources_** on _**/tmp**_ in your sandbox

Copy offense_codes.csv _**src/main/resources**_ on **_/tmp_** in your sandbox


**Build**


cd  to-jar-path-on-target-scala

_run command:_  sbt package


**Run** 

./run.sh  path-to-jar

_default:_

./run.sh  /tmp/spark-hive-interview-0.1.jar