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
 *使用storm实现累计求和
 * @author mazexiang
 * @version $Id: LocalSumStormTopology.java, v 0.1 2018年12月09日 20:01 mazexiang Exp $
 */
public class LocalSumStormTopology {

    /**
     * 定义数据源, Spout需要继承BaseRichSpout
     * 数据源需要产生数据,并发射出去
     */
    public static class DataSourceSpout extends BaseRichSpout{

        private SpoutOutputCollector spoutOutputCollector;
        /**
         * 初始化方法, 只会被调用一次
         * @param map 配置参数
         * @param topologyContext  上下文
         * @param spoutOutputCollector    数据发射器
         */
        public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
            this.spoutOutputCollector = spoutOutputCollector;
        }

        int number = 0;

        /**
         * 核心方法, 产出数据,
         * 在生产上肯定是从消息队列中获取数据
         *
         * 这个方法是一个死循环, 一直执行
         */
        public void nextTuple() {
            this.spoutOutputCollector.emit(new Values(number++));
            System.out.println("spout: "+number);
            //防止数据发送太快
            org.apache.storm.utils.Utils.sleep(1000);
        }

        /**
         * 申明输出字段
         * @param outputFieldsDeclarer
         */
        public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
            outputFieldsDeclarer.declare(new Fields("num"));
        }
    }

    /**
     * 数据累计求和bolt, 接受数据并处理
     */
    public static class SumBolt extends BaseRichBolt{

        /**
         * 初始化方法, 会被执行一次
         * @param map
         * @param topologyContext
         * @param outputCollector
         */
        public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {

        }


        int sum = 0;
        /**
         * 死循环方法, 职责是 获取spout发送过来的数据, 处理数据
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
        //模拟创建一个本机的storm集群
        cluster.submitTopology("LocalSumStormTopology",new Config(),builder.createTopology());
    }
}