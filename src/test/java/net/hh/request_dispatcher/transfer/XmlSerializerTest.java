package net.hh.request_dispatcher.transfer;

import junit.framework.Assert;
import org.junit.Test;

import java.io.Serializable;

/**
 * Created by hartmann on 4/2/14.
 */
public class XmlSerializerTest {

    private Serializer ser = new XmlSerializer();

    public void testSerialize(Serializable o) throws Exception {
        Assert.assertEquals(o, ser.deserialize(ser.serialize(o)));
    }

    @Test
    public void testString() throws Exception {
        testSerialize("Hello World");
    }

    @Test
    public void testInt() throws Exception {
        testSerialize(new Integer(125123));
    }

    @Test
    public void testComplexObject() throws Exception {
        class Test implements Serializable {
            String msg;
            int    bla;

            Test(String msg, int bla) {
                this.msg = msg;
                this.bla = bla;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj instanceof Test) {
                    Test t = (Test) obj;
                    return t.msg.equals(msg) && t.bla == bla;
                } else {
                    return false;
                }
            }
        }

        testSerialize(new Test("hi", 1));
    }
}
