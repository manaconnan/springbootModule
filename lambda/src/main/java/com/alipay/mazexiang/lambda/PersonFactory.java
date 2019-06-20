/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.mazexiang.lambda;

/**
 *
 * @author mazexiang
 * @version $Id: PersonFactory.java, v 0.1 2019Äê05ÔÂ31ÈÕ 16:32 mazexiang Exp $
 */

@FunctionalInterface
public interface PersonFactory<P extends  Person> {
    P create(String firstName, String lastName);
}