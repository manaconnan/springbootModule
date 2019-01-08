/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang;

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

import java.util.Map;

/**
 *ʹ��stormʵ���ۼ����
 * @author mazexiang
 * @version $Id: LocalSumStormTopology.java, v 0.1 2018��12��09�� 20:01 mazexiang Exp $
 */
public class LocalSumStormTopology {

    /**
     * ��������Դ, Spout��Ҫ�̳�BaseRichSpout
     * ����Դ��Ҫ��������,�������ȥ
     */
    public static class DataSourceSpout extends BaseRichSpout{

        private SpoutOutputCollector spoutOutputCollector;
        /**
         * ��ʼ������, ֻ�ᱻ����һ��
         * @param map ���ò���
         * @param topologyContext  ������
         * @param spoutOutputCollector    ���ݷ�����
         */
        public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
            this.spoutOutputCollector = spoutOutputCollector;
        }

        int number = 0;

        /**
         * ���ķ���, ��������,
         * �������Ͽ϶��Ǵ���Ϣ�����л�ȡ����
         *
         * ���������һ����ѭ��, һֱִ��
         */
        public void nextTuple() {
            this.spoutOutputCollector.emit(new Values(number++));
            System.out.println("spout: "+number);
            //��ֹ���ݷ���̫��
            org.apache.storm.utils.Utils.sleep(1000);
        }

        /**
         * ��������ֶ�
         * @param outputFieldsDeclarer
         */
        public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
            outputFieldsDeclarer.declare(new Fields("num"));
        }
    }

    /**
     * �����ۼ����bolt, �������ݲ�����
     */
    public static class SumBolt extends BaseRichBolt{

        /**
         * ��ʼ������, �ᱻִ��һ��
         * @param map
         * @param topologyContext
         * @param outputCollector
         */
        public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {

        }


        int sum = 0;
        /**
         * ��ѭ������, ְ���� ��ȡspout���͹���������, ��������
         * @param tuple
         */
        public void execute(Tuple tuple) {
           Integer value =  tuple.getIntegerByField("num");

            sum += value;

            System.out.println("sum = "+sum);
        }

        public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

        }
    }

    public static void main(String[] args) {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("DataSourceSpout",new DataSourceSpout());
        builder.setBolt("SumBolt",new SumBolt()).shuffleGrouping("DataSourceSpout");

        LocalCluster cluster = new LocalCluster();
        //ģ�ⴴ��һ��������storm��Ⱥ
        cluster.submitTopology("LocalSumStormTopology",new Config(),builder.createTopology());
    }
}