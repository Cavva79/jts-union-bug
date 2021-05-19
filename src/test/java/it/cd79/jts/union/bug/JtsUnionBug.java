package it.cd79.jts.union.bug;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.locationtech.jts.geom.Geometry;
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

		assertEquals("Unions are different", union1.toText(), union2.toText());
	}
}
