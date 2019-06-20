/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.search.textsimilarity;

import com.alibaba.search.alinlpsdk.protocol.ModelContext;
import com.alibaba.search.alinlpsdk.protocol.model.api.TextSimilarity;
import com.alibaba.search.alinlpsdk.protocol.model.api.returntype.TextSimilarityItem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mazexiang
 * @version $Id: TextSimilaritySample.java, v 0.1 2019年04月02日 20:54 mazexiang Exp $
 */
public class TextSimilaritySample implements TextSimilarity {

    public List<TextSimilarityItem> calcSimilarity(String s, String s1) throws Exception {
        List<TextSimilarityItem> textSimilarityItems = new ArrayList<TextSimilarityItem>();
        Double score = 0.0;

        if (null == s || null == s1 ||
            "".equals(s) || "".equals(s1)){
            score = 0.5;
        }
        else if(s.equals(s1)){
            score = 1.0;
        }
        textSimilarityItems.add(new TextSimilarityItem(score));
        return textSimilarityItems;
    }

    public void init(ModelContext modelContext) throws Exception {

    }
}