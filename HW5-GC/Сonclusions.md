#### Анализ использования GC: G1 & CMS

Эксперименты были проведены с командами для vm:  
```
-Xmx1500m
-Xmx4500m
-XX:+UseG1GC
-XX:+UseConcMarkSweepGC
-XX:MaxGCPauseMillis=100, 60, 40 (для G1)
-XX:NewRatio=1, 3, 5 (для CMS)
-Xlog:gc*,gc+ref=debug,gc+heap=debug,gc+age=trace:file=/home/tim/IdeaProjects/OtusJava/HW5-GC/logs/gc-%p-%t.log:tags,uptime,time,level:filecount=5,filesize=10m
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=./logs/dump 
```
Каждый лог файл был проанализирован с помощью сайта https://gceasy.io/. 
Результаты были сведены в краткую таблицу:

Max Heap Size 4.5Gb:

| Param          | G1 100ms| G1 60ms | G1 40ms| CMS 1 | CMS 3 |CMS 5 |
|:-------------  |:-------:|:--------:|:----------:|----:|--------:|------:|
|Avg Pause GC Time|8.71 ms|7.83 ms|7.84 ms|64.5 ms|41.1 ms|8.71 ms |
|Max Pause GC Time|2 sec 841 ms|2 sec 841 ms|2 sec 841 ms|1 sec 410 ms|2 sec 350 ms|2 sec 841 ms|
|Throughput       |94.075% |96.781%|97.201%|80.769%|67.158%|94.075%|

Max Heap Size 1.5Gb:

| Param          | G1 100ms| G1 60ms | G1 40ms| CMS 1 | CMS 3 |CMS 5 |
|:-------------  |:-------:|:--------:|:----------:|----:|--------:|------:|
|Avg Pause GC Time|8.71 ms|7.83 ms|7.84 ms|64.5 ms|41.1 ms|8.71 ms |
|Max Pause GC Time|2 sec 841 ms|2 sec 841 ms|2 sec 841 ms|1 sec 410 ms|2 sec 350 ms|2 sec 841 ms|
|Throughput       |94.075% |96.781%|97.201%|80.769%|67.158%|94.075%|


Вывод: по моему мнению лучшим gc является G1. Так как у него самый большой индекс Throughput, 
и минимальная задержка на уборку мусора.