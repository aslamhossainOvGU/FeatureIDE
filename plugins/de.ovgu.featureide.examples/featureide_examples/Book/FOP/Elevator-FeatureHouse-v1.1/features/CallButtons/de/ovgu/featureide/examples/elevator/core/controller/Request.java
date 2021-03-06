/*******************************************************************************
 * FeatureIDE - A Framework for Feature-Oriented Software Development
 * Copyright (C) ${creation_year}-${revision_year}  FeatureIDE team, University of Magdeburg, Germany
 *
 * This file is part of FeatureIDE.
 *
 * FeatureIDE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FeatureIDE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FeatureIDE.  If not, see <http://www.gnu.org/licenses/>.
 * See http://featureide.cs.ovgu.de/ for further information.
 *******************************************************************************/
package de.ovgu.featureide.examples.elevator.core.controller;

import java.util.Comparator;

/**
 * 
 * This class represents a request of a specific floor.
 * 
 * @author FeatureIDE Team
 */
public class Request {

	private int floor;

	public int getFloor() {
		return floor;
	}

	public Request(int floor) {
		this.floor =
			floor;
	}

	public static class RequestComparator implements Comparator<Request> {

	}

	@Override
	public String toString() {
		return "Request [floor="
			+ floor + "]";
	}

}
