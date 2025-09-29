public final class Card {

    // Raw fields (make them final if you can)
    final String name;
    final int id;
    final String type;
    final String subtype;
    final int dateY;
    final int dateM;
    final int dateD;

    // Cached derived fields
    final int typeWeight; // 0..17 per your scheme
    final int dateKey; // yyyyMMdd for fast compare

    public Card(String name,
            int id,
            String type,
            String subtype,
            int dateY,
            int dateM,
            int dateD) {

        if (name == null || type == null) {
            throw new IllegalArgumentException("Incorrect type and subtype");
        }
        this.name = name;
        this.id = id;
        this.type = type;
        this.subtype = (subtype == null ? "" : subtype);
        this.dateY = dateY;
        this.dateM = dateM;
        this.dateD = dateD;

        this.typeWeight = computeTypeWeight(this.type, this.subtype);
        this.dateKey = computeDateKey(dateY, dateM, dateD);
    }

    private static int computeDateKey(int y, int m, int d) {
        // (Optional) could validate ranges if needed
        return y * 10000 + m * 100 + d;
    }

    private static int computeTypeWeight(String t, String st) {
        switch (t) {
            case "Skrimsli":
                switch (st) {
                    case "Venjulegt":
                        return 0;
                    case "Ahrifa":
                        return 1;
                    case "Bodunar":
                        return 2;
                    case "Samruna":
                        return 3;
                    case "Samstillt":
                        return 4;
                    case "Thaeo":
                        return 5;
                    case "Penduls":
                        return 6;
                    case "Tengis":
                        return 7;
                    default:
                        throw new IllegalArgumentException("Unknown Skrimsli subtype: " + st);
                }
            case "Galdur":
                switch (st) {
                    case "Venjulegur":
                        return 8;
                    case "Bunadar":
                        return 9;
                    case "Svida":
                        return 10;
                    case "Samfelldur":
                        return 11;
                    case "Bodunar":
                        return 12;
                    case "Hradur":
                        return 13;
                    default:
                        throw new IllegalArgumentException("Unknown Galdur subtype: " + st);
                }
            case "Gildra":
                switch (st) {
                    case "Venjuleg":
                        return 14;
                    case "Samfelld":
                        return 15;
                    case "Mot":
                        return 16;
                    default:
                        throw new IllegalArgumentException("Unknown Gildra subtype: " + st);
                }
            case "Annad":
                // No subtype (ignore whatever was passed)
                return 17;
            default:
                throw new IllegalArgumentException("Unknown type: " + t);
        }
    }

    // Convert priority strings array into int codes once.
    // 0 = name, 1 = id, 2 = flokkur(type/subtype weight), 3 = dagsetning
    public static int[] toPriorityOrder(String[] priority) {
        if (priority == null) {
            throw new IllegalArgumentException("priority array must not be null");
        }
        int[] order = new int[priority.length];
        for (int i = 0; i < priority.length; i++) {
            order[i] = priorityCode(priority[i]);
        }
        return order;
    }

    private static int priorityCode(String p) {
        switch (p) {
            case "nafn":
                return 0;
            case "id":
                return 1;
            case "flokkur":
                return 2;
            case "dagsetning":
                return 3;
            default:
                throw new IllegalArgumentException(
                        "Priority must be one of [nafn, id, flokkur, dagsetning], got: " + p);
        }
    }

    // Original style: boolean "less" function (strictly less).
    // Uses precomputed int[] priority codes instead of String[] each time.
    public static boolean cardLess(Card a, Card b, int[] priOrder) {
        for (int code : priOrder) {
            switch (code) {
                case 0: { // name
                    int cmp = a.name.compareTo(b.name);
                    if (cmp < 0)
                        return true;
                    if (cmp > 0)
                        return false;
                    break;
                }
                case 1: { // id
                    if (a.id < b.id)
                        return true;
                    if (a.id > b.id)
                        return false;
                    // If IDs can be equal, continue. If they must be unique, validate elsewhere.
                    break;
                }
                case 2: { // type/subtype
                    if (a.typeWeight < b.typeWeight)
                        return true;
                    if (a.typeWeight > b.typeWeight)
                        return false;
                    break;
                }
                case 3: { // date
                    if (a.dateKey < b.dateKey)
                        return true;
                    if (a.dateKey > b.dateKey)
                        return false;
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected priority code: " + code);
            }
        }
        // Equal in all priority dimensions: a is not strictly less than b
        return false;
    }
}
