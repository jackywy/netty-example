package com.wy.example.netty.example712;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * MessagePack测试
 *
 * @author Jacky
 * @version 1.0
 * @create 2016-12-30  16:14
 **/
public class MessagePackTest {
    public static final Logger logger = LoggerFactory.getLogger(MessagePackTest.class);

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<String>();
        list.add("msgpack");
        list.add("kumofs");
        list.add("viver");
        MessagePack messagePack = new MessagePack();
        //Serialize
        byte[] raw = messagePack.write(list);
        List<String> dst1 = messagePack.read(raw, Templates.tList(Templates.TString));
        logger.info(dst1.get(0));
        logger.info(dst1.get(1));
        logger.info(dst1.get(2));

    }
}
