/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.mazexiang.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author mazexiang
 * @version $Id: Person.java, v 0.1 2019Äê05ÔÂ31ÈÕ 16:31 mazexiang Exp $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {
   private String firstName;
   private String lastName;
}