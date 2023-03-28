package com.tekion.gameofcricket.utility;

import com.tekion.gameofcricket.models.Player;
import org.bson.types.ObjectId;

import java.util.UUID;

public class RandomFactory {
    public static Player generateRandomPlayer() {
        return new Player(generateRandomName());
    }

    public static String generateRandomName() {
        return UUID.randomUUID().toString();
    }

    public static ObjectId generateRandomId() {
        return ObjectId.get();
    }
}
