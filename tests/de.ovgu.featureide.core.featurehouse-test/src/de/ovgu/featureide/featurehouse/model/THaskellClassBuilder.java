/* FeatureIDE - An IDE to support feature-oriented software development
 * Copyright (C) 2005-2011  FeatureIDE Team, University of Magdeburg
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see http://www.gnu.org/licenses/.
 *
 * See http://www.fosd.de/featureide/ for further information.
 */
package de.ovgu.featureide.featurehouse.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * TODO description
 * 
 * @author Jens Meinicke
 */
public class THaskellClassBuilder {
	private HaskellClassBuilder builder = new HaskellClassBuilder(null);
	
	// METHOD TEST 1
	private String TEST_METHOD_1 = "mapResult :: (a -> Result b err) -> Result a err -> Result b err"; 
	private String EXPECTED_NAME_METHOD_1 = "mapResult";
	private String EXPECTED_PARAMETER_1_METHOD_1 = "(a -> Result b err) -> Result a err -> Result b err";
	
	@Test
	public void MethodTest1() {
		assertEquals(EXPECTED_NAME_METHOD_1, builder.getMethod(TEST_METHOD_1).get(0));
		assertEquals(EXPECTED_PARAMETER_1_METHOD_1, builder.getMethod(TEST_METHOD_1).get(1));
	}
}