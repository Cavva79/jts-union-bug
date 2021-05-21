package org.locationtech.jts.geom;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

public class JtsUnionBug {

	@Test
	public void unionTest() throws ParseException {
		WKTReader reader = new WKTReader();

		Geometry polygon1 = reader.read(
				"POLYGON ((1740002.28 4981731.2, 1740011.15 4981551.29, 1740040.91 4981553.13, 1740095.86 4981555.41, 1740327.08 4981566.69, 1740318.17 4981748.35, 1740098.29 4981735.6, 1740002.28 4981731.2))");
		Geometry polygon2 = reader.read(
				"POLYGON ((1740002.2800000003 4981731.2, 1740006.7150060183 4981641.24487793, 1740002.28 4981731.2, 1740001.97 4981734.51, 1740002.2800000003 4981731.2))");

		Geometry union1 = polygon1.union(polygon2);
		Geometry union2 = polygon2.union(polygon1);
		
		List<Geometry> listToUnion = new ArrayList<>();
		listToUnion.add(polygon1);
		listToUnion.add(polygon2);
		
		
		Geometry toBeUnited1 = polygon1.getFactory().buildGeometry(listToUnion);
		Geometry union3 = toBeUnited1.union();
		
		Collections.reverse(listToUnion);
		Geometry toBeUnited2 = polygon1.getFactory().buildGeometry(listToUnion);

		Geometry union4 = toBeUnited2.union();
		
		GeometryOverlay.setOverlayImpl(GeometryOverlay.OVERLAY_PROPERTY_VALUE_NG);

		Geometry union5 = polygon1.union(polygon2);
		Geometry union6 = polygon2.union(polygon1);
		
		Geometry toBeUnited3 = polygon1.getFactory().buildGeometry(listToUnion);
		Geometry union7 = toBeUnited3.union();
		
		Collections.reverse(listToUnion);
		Geometry toBeUnited4 = polygon1.getFactory().buildGeometry(listToUnion);

		Geometry union8 = toBeUnited4.union();

		
		System.out.print("union1 = ");
		System.out.println(union1);
		System.out.print("union2 = ");
		System.out.println(union2);
		System.out.print("union3 = ");
		System.out.println(union3);
		System.out.print("union4 = ");
		System.out.println(union4);
		
		System.out.print("union5 = ");
		System.out.println(union5);
		System.out.print("union6 = ");
		System.out.println(union6);
		System.out.print("union7 = ");
		System.out.println(union7);
		System.out.print("union8 = ");
		System.out.println(union8);
		
		assertEquals("Unions are different", union1.toText(), union2.toText());
	}
}
