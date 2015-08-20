/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.dataimporttool.dmt.util;

import java.util.HashMap;
import java.util.Map;

import org.openmrs.module.dataimporttool.dmt.helper.SystemException;

/**
 * This class create pools of TOPs, it is used to access a TOP value from a
 * tuple that has no parenthood with the TOP that it wants to access the value
 *
 */
public class KeyPoolUtil {
	private static final Map<Integer/* Tuple */, Map<Object/* CURR */, Integer/* TOP */>> keyPools = new HashMap<Integer, Map<Object, Integer>>();

	public KeyPoolUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Add a value to an existing pool. It creates the pool if doesn't exist
	 * 
	 * @param tuple
	 * @param curr
	 * @param top
	 */
	public void putPool(Integer tuple, Object curr, Integer top) {
		Map<Object, Integer> keyPool = keyPools.get(tuple);

		if (keyPool == null) {
			keyPool = new HashMap<Object, Integer>();
			keyPools.put(tuple, keyPool);
		}
		keyPool.put(curr, top);
	}

	/**
	 * Retrieve a pooled TOP value
	 * 
	 * @param tuple
	 * @param curr
	 * @return
	 * @throws SystemException
	 */
	public Integer getPoolValues(Integer tuple, Object curr)
			throws SystemException {
		Map<Object, Integer> keyPool = keyPools.get(tuple);

		if (keyPool == null) {
			throw new SystemException("The pool of keys for Tuple: "
					+ tuple.intValue() + " doesn't exist");
		}

		Integer top = keyPool.get(curr);

		if (top == null) {
			throw new SystemException("The pool of keys for CURR: "
					+ curr.toString() + " doesn't exist");
		}
		return top;
	}
}
