package classesAuxiliaresGeral;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.maven.shared.utils.StringUtils;

import junitparams.mappers.DataMapper;

public class ParametersMapper implements DataMapper {

	@Override
	public Object[] map(Reader reader) {

		try {
			BufferedReader br = new BufferedReader(reader);
			List<Map<String, String>> result = new LinkedList<Map<String, String>>();
			String header = br.readLine();
			if (StringUtils.isNotBlank(header)) {
				String[] columNames = header.split(";");
				String line = null;
				while (StringUtils.isNotBlank(line = br.readLine())) {
					Map<String, String> map = new HashMap<String, String>();
					String[] values = line.split(";");
					int index = 0;
					for (String column : columNames) {
						if (index < values.length) {
							map.put(column, values[index]);
							index++;
						}
					}
					result.add(map);
				}
			} else {
				throw new IllegalArgumentException("File csv without data.");
			}
			return result.toArray();
		} catch (IOException e) {
			throw new RuntimeException("Unable to read from csv file.", e);
		}
	}
}