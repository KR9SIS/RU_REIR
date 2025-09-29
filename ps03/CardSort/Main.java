public class Main {
    // private static class Card {
    // String name;
    // int id;
    // String type;
    // String subtype;
    // int dateY;
    // int dateM;
    // int dateD;
    //
    // private static int getPriority(String pStr) {
    // switch (pStr) {
    // case "nafn":
    // return 0;
    // case "id":
    // return 1;
    // case "flokkur":
    // return 2;
    // case "dagsetning":
    // return 3;
    //
    // default:
    // throw new RuntimeException("Priority Array must only contain [nafn, id,
    // flokkur, dagsetning]");
    // }
    // }
    //
    // private static int getTypeWeight(String tStr, String stStr) {
    // switch (tStr) {
    // case "Skrimsli":
    // switch (stStr) {
    // case "Venjulegt":
    // return 0;
    // case "Ahrifa":
    // return 1;
    // case "Bodunar":
    // return 2;
    // case "Samrunar":
    // return 3;
    // case "Samstillt":
    // return 4;
    // case "Thaeo":
    // return 5;
    // case "Penduls":
    // return 6;
    // case "Tengis":
    // return 7;
    // default:
    // break;
    // }
    // case "Galdur":
    // switch (stStr) {
    // case "Venjulegt":
    // return 8;
    // case "Bunadar":
    // return 9;
    // case "Svida":
    // return 10;
    // case "Samfelldur":
    // return 11;
    // case "Bodunar":
    // return 12;
    // case "Hradur":
    // return 13;
    // default:
    // break;
    // }
    // case "Gildra":
    // switch (stStr) {
    // case "Venjulegt":
    // return 14;
    // case "Samfelld":
    // return 15;
    // case "Mot":
    // return 16;
    // default:
    // break;
    // }
    // case "Annad":
    // return 17;
    //
    // default:
    // throw new RuntimeException("Incorrect type and subtype");
    // }
    // }
    //
    // private static boolean cardLess(Card first, Card second, String[] priority) {
    // int i = 0;
    // while (i < priority.length) {
    // int idx = getPriority(priority[i]);
    // switch (idx) {
    // case 0:
    // if (first.name.compareTo(second.name) < 0) {
    // return true;
    // } else if (first.name.compareTo(second.name) > 0) {
    // return false;
    // }
    // i++;
    // break;
    // case 1:
    // if (first.id < second.id) {
    // return true;
    // } else if (first.id > second.id) {
    // return false;
    // }
    // throw new RuntimeException("No two cards may have the same id");
    // case 2:
    // if (getTypeWeight(first.type, first.subtype) < getTypeWeight(second.type,
    // second.subtype)) {
    // return true;
    // } else if (getTypeWeight(first.type, first.subtype) >
    // getTypeWeight(second.type,
    // second.subtype)) {
    // return false;
    // }
    // i++;
    // break;
    // case 3:
    // if (first.dateY < second.dateY) {
    // return true;
    // } else if (first.dateY > second.dateY) {
    // return false;
    // }
    // if (first.dateM < second.dateM) {
    // return true;
    // } else if (first.dateM > second.dateM) {
    // return false;
    // }
    // if (first.dateD < second.dateD) {
    // return true;
    // } else if (first.dateD > second.dateD) {
    // return false;
    // }
    // i++;
    // break;
    //
    // default:
    // break;
    // }
    // }
    //
    // return false;
    // }
    // }
    //
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        Card[] cards = new Card[n];
        for (int i = 0; i < n; i++) {
            String[] cardStrArr = io.getLine().split(",");
            for (int j = 0; j < cardStrArr.length; j++) {
                cardStrArr[j] = cardStrArr[j].strip();
            }

            String name = cardStrArr[0];

            int id = Integer.parseInt(cardStrArr[1]);
            String type, subType;
            String[] tmp_arr = cardStrArr[2].split(" - ");
            if (tmp_arr.length == 1) {
                type = tmp_arr[0].strip();
                subType = "";
            } else {
                type = tmp_arr[0].strip();
                subType = tmp_arr[1].strip();
            }

            tmp_arr = cardStrArr[3].split("-");
            int dateY = Integer.parseInt(tmp_arr[0]);
            int dateM = Integer.parseInt(tmp_arr[1]);
            int dateD = Integer.parseInt(tmp_arr[2]);

            cards[i] = new Card(name, id, type, subType, dateY, dateM, dateD);
        }

        String priority = io.getLine();
        int[] priority_arr = Card.toPriorityOrder(priority.split(" "));

        sortCards(cards, priority_arr);

        for (Card card : cards) {
            io.println(card.name);
        }

        io.close();
    }

    private static void sortCards(Card[] arr, int[] priority) {

        Card[] aux = new Card[arr.length];
        sort(arr, aux, 0, arr.length - 1, priority);

    }

    private static void sort(Card[] a, Card[] aux, int lo, int hi, int[] priority) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid, priority);
        sort(a, aux, mid + 1, hi, priority);
        merge(a, aux, lo, mid, hi, priority);
    }

    private static void merge(Card[] a, Card[] aux, int lo, int mid, int hi, int[] priority) {
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (Card.cardLess(aux[j], aux[i], priority)) {
                // If aux[j] < aux[i]
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

}
