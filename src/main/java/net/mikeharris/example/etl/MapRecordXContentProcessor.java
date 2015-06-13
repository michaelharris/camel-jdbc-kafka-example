package net.mikeharris.example.etl;

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Records (maps) from the database are prepared for ElasticSearch
 * 
 */
public class MapRecordXContentProcessor implements Processor {

	static Logger log = LoggerFactory
			.getLogger(MapRecordXContentProcessor.class);

	public void process(Exchange msg) throws IOException {
		log.info("Processing msg {}", msg);

		Message in = msg.getIn();
		Class cls = msg.getClass();
		System.out.println("The type of the object is: " + cls.getName());
		Map<String, Object> record = msg.getIn().getBody(Map.class);

		log.info("Processing record {}", record);

		// An example of building json
		// XContentBuilder builder = XContentFactory.jsonBuilder()
		// .startObject()
		// .field("user", "kimchy")
		// .field("postDate", new Date())
		// .field("message", "trying out Elasticsearch")
		// .endObject();

		XContentBuilder builder = XContentFactory.jsonBuilder().map(record);

		in.setBody(builder);

		log.debug("xcontentbuilder" + builder.prettyPrint().string());

		// Do something useful with this record.
	}
}