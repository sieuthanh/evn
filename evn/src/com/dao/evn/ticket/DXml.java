//package com.dao.evn.ticket;
//
//import com.form.evn.ticket.FTicket;
//
//import java.io.FileOutputStream;
//
//import javax.xml.stream.XMLEventFactory;
//import javax.xml.stream.XMLEventWriter;
//import javax.xml.stream.XMLOutputFactory;
//import javax.xml.stream.XMLStreamException;
//import javax.xml.stream.events.Characters;
//import javax.xml.stream.events.EndElement;
//import javax.xml.stream.events.StartDocument;
//import javax.xml.stream.events.StartElement;
//import javax.xml.stream.events.XMLEvent;
//
//public class DXml {
//  private String configFile;
//
//  public void setFile(String configFile) {
//    this.configFile = configFile;
//  }
//
//  public void saveConfig(FTicket bean) throws Exception {
//    // Create a XMLOutputFactory
//    XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
//    // Create XMLEventWriter
//    XMLEventWriter eventWriter = outputFactory
//        .createXMLEventWriter(new FileOutputStream(configFile));
//    // Create a EventFactory
//    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
//    XMLEvent end = eventFactory.createDTD("\n");
//    // Create and write Start Tag
//    StartDocument startDocument = eventFactory.createStartDocument();
//    eventWriter.add(startDocument);
//
//    // Create config open tag
//    StartElement configStartElement = eventFactory.createStartElement("",
//        "", "getTicket");
//    eventWriter.add(configStartElement);
//    eventWriter.add(end);
//    // Write the different nodes
//    createNode(eventWriter, "ticket_id", bean.getTicket_id()+"");
//    createNode(eventWriter, "ticket_id_err", bean.getTicket_id_err()+"");
//    createNode(eventWriter, "src_connect_id",  bean.getSrc_connect_id()+"");
//    createNode(eventWriter, "status",  bean.getStatus());
//    createNode(eventWriter, "active", bean.getActive()+"");
//      createNode(eventWriter, "evn_id", bean.getEvn_id()+"");
//      createNode(eventWriter, "evn_time", bean.getEvn_time());
//      createNode(eventWriter, "buffer_id", bean.getBuffer_id()+"");
//      createNode(eventWriter, "buffer_time", bean.getBuffer_time());
//      createNode(eventWriter, "ebs_id", bean.getEbs_id()+"");
//      createNode(eventWriter, "ebs_time", bean.getEbs_time());
//      createNode(eventWriter, "start_time", bean.getStart_time());
//      createNode(eventWriter, "end_time", bean.getEnd_time());
//      createNode(eventWriter, "total_records", bean.getTotal_records()+"");
//      createNode(eventWriter, "code", bean.getCode());
//      createNode(eventWriter, "table_name", bean.getTable_name());
//      createNode(eventWriter, "wheres", bean.getWheres()+"");
//      createNode(eventWriter, "description", bean.getDescription());
//
//    eventWriter.add(eventFactory.createEndElement("", "", "getTicket"));
//    eventWriter.add(end);
//    eventWriter.add(eventFactory.createEndDocument());
//    eventWriter.close();
//  }
//
//  private void createNode(XMLEventWriter eventWriter, String name,
//      String value) throws XMLStreamException {
//
//    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
//    XMLEvent end = eventFactory.createDTD("\n");
//    XMLEvent tab = eventFactory.createDTD("\t");
//    // Create Start node
//    StartElement sElement = eventFactory.createStartElement("", "", name);
//    eventWriter.add(tab);
//    eventWriter.add(sElement);
//    // Create Content
//    Characters characters = eventFactory.createCharacters(value);
//    eventWriter.add(characters);
//    // Create End node
//    EndElement eElement = eventFactory.createEndElement("", "", name);
//    eventWriter.add(eElement);
//    eventWriter.add(end);
//
//  }
//
//} 