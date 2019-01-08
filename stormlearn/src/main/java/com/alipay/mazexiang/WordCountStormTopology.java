/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang;

import org.apache.commons.io.FileUtils;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mazexiang
 * @version $Id: WordCountStormTopology.java, v 0.1 2018��12��15�� 12:38 mazexiang Exp $
 */
public class WordCountStormTopology {

    public static class DataSourceSpout extends BaseRichSpout{

        private SpoutOutputCollector spoutOutputCollector;
        public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
            this.spoutOutputCollector = spoutOutputCollector;
        }

        public void nextTuple() {
            //��ȡ�ļ���Ŀ¼�µ�����txtβ׺���ļ�
            String dir  =  "/Users/mazexiang/IdeaProjects/springbootModule/stormlearn/src/main/resources";
            Collection<File> files = FileUtils.listFiles(new File(dir), new String[] {"txt"}, false);
            files.stream().forEach(file -> {

                try {
                    List<String> list = FileUtils.readLines(file);
                    list.stream().forEach( line->
                            this.spoutOutputCollector.emit(new Values(line))
                    );

                    //�����ļ���,�����ظ�����
                    FileUtils.moveFile(file,new File(file.getAbsolutePath()+"."+System.currentTimeMillis()));

                } catch (IOException e) {
                    e.printStackTrace();
                }


            });

        }

        public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
            outputFieldsDeclarer.declare(new Fields("line"));
        }
    }

    /**
     * �����ݽ��зָ�
     */
    public static class SplitBolt extends BaseRichBolt{

        private OutputCollector outputCollector;
        @Override
        public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
            this.outputCollector = outputCollector;
        }

        @Override
        public void execute(Tuple tuple) {
            String line = tuple.getStringByField("line");

            String replace = line.replace(" ", ",");
            String[] words = replace.split(",");

            Arrays.stream(words).forEach(word->{
                this.outputCollector.emit(new Values(word));
            });

        }

        @Override
        public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
            outputFieldsDeclarer.declare(new Fields("word"));
        }
    }

    /**
     * ��Ƶ����bolt
     */
    public static class CountBolt extends BaseRichBolt{

        @Override
        public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {

        }

        Map<String,Integer> wordCount = new HashMap<> ();
        @Override
        public void execute(Tuple tuple) {
            String word = tuple.getStringByField("word");
            if (wordCount.containsKey(word)){
                wordCount.put(word,wordCount.get(word)+1);
            }else {
                wordCount.put(word,1);
            }

            System.out.println("~~~~~~~~~~~~~~");

            wordCount.entrySet().stream().forEach(stringIntegerEntry -> {

                System.out.println(stringIntegerEntry.getKey()+":"+stringIntegerEntry.getValue());
            });


        }

        @Override
        public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

        }
    }

    public static void main(String[] args) {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("DataSourceSpout",new DataSourceSpout());
        builder.setBolt("SplitBolt",new SplitBolt()).shuffleGrouping("DataSourceSpout");
        builder.setBolt("CountBolt",new CountBolt()).shuffleGrouping("SplitBolt");

        LocalCluster cluster = new LocalCluster();
        //ģ�ⴴ��һ��������storm��Ⱥ
        cluster.submitTopology("WordCountStormTopology",new Config(),builder.createTopology());

    }


}