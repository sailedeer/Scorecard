package sailedeer.scorecard.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Eli on 3/13/2017.
 */

public class Serializer {

    public static byte[] serialize(Object o) throws IOException
    {
        try (ByteArrayOutputStream bo = new ByteArrayOutputStream())
        {
            try (ObjectOutputStream os = new ObjectOutputStream(bo))
            {
                os.writeObject(o);
            }
            return bo.toByteArray();
        }
    }

    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException
    {
        try(ByteArrayInputStream bi = new ByteArrayInputStream(bytes)){
            try(ObjectInputStream oi = new ObjectInputStream(bi))
            {
                return oi.readObject();
            }
        }
    }
}
