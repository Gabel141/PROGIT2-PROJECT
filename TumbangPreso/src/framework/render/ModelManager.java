package framework.render;

import java.util.HashMap;

public class ModelManager {
 
    private static final HashMap<String, Model> models = new HashMap<>();

    public static final String ATTACKER = "attacker";
    public static final String DEFENDER = "defender";
    public static final String SLIPPER = "slipper";
    public static final String CAN = "can";

    public static void init() {
        System.out.println("[Render/ModelManager]: Initializing...");

        models.put(ATTACKER, new Model(48, 48, ATTACKER));
        models.put(DEFENDER, new Model(48, 48, DEFENDER));
        models.put(SLIPPER, new Model(20, 20, SLIPPER));
        models.put(CAN, new Model(48, 48, CAN));
    }

    public static Model model(String key) {
        return models.get(key);
    }


}
