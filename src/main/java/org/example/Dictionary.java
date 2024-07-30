package org.example;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {
    static final Map<String, String> Mp4PlayableCharactersMapping = new HashMap<>();
//0b = new

    static {
        Mp4PlayableCharactersMapping.put("Bowser", "42 6F 77 73 65 72");
        Mp4PlayableCharactersMapping.put("pièce", "70 69 D5 63 65");
//        Mp4PlayableCharactersMapping.put("Mario", "4D 61 72 69 6F");
//        Mp4PlayableCharactersMapping.put("Luigi", "4C 75 69 67 69");
//        Mp4PlayableCharactersMapping.put("Peach", "50 65 61 63 68");
//        Mp4PlayableCharactersMapping.put("Yoshi", "59 6F 73 68 69");
//        Mp4PlayableCharactersMapping.put("Wario", "57 61 72 69 6F");
//        Mp4PlayableCharactersMapping.put("DK", "44 4B");
//        Mp4PlayableCharactersMapping.put("Daisy", "44 61 69 73 79");
//        Mp4PlayableCharactersMapping.put("Waluigi", "57 61 6C 75 69 67 69");

    }




    /*
    index de noms de perso
    0000
    index de Moquerie de…
    00003000
    00003100
    index de ___ peut maintenant se moquer…
    00003E40
    00004310

    index de Voix de __ (01 début, 0E fin)
    0000CDF0
    0000CF00

    index de ___ heureux, ____ triste (NPC)
    0000D820
    0000DC10

    index de noms de perso (01 début 06 fin)
    00013B40
    00013BD0

    index de noms de perso (01 début 07 fin)
    0002D8D0
    0002DE00

    Maskass:
    00004440

    Mini-jeux:
    06 début
    1E fin

    000049C0
    00004C60


    Narration début:
    00006490
    00006850

    Narration fin:
    00006890
    00006B80



     */


    public static final Map<Character, Integer> marioPartyMapping = new HashMap<>();

    /*
    à exclure

    01= start
    02= start
    06= end
    0A= end
    0C = end
    15 = end
    FF
    18
    02
    85
    C3
    1E
    1D

    */

    static {
        marioPartyMapping.put(' ', 0x10);
        marioPartyMapping.put('1', 0x31);
        marioPartyMapping.put('2', 0x32);
        marioPartyMapping.put('3', 0x33);
        marioPartyMapping.put('4', 0x34);
        marioPartyMapping.put('5', 0x35);
        marioPartyMapping.put('6', 0x36);
        marioPartyMapping.put('7', 0x37);
        marioPartyMapping.put('8', 0x38);
        marioPartyMapping.put('9', 0x39);
        marioPartyMapping.put('…', 0x85);
        marioPartyMapping.put('œ', 0xE8);
        marioPartyMapping.put('ù', 0xDD);
        marioPartyMapping.put('ô', 0xDB);
        marioPartyMapping.put('è', 0xD5);
        marioPartyMapping.put('é', 0xD6);
        marioPartyMapping.put('ê', 0xD7);
        marioPartyMapping.put('î', 0xD9);
        marioPartyMapping.put('à', 0xD1);
        marioPartyMapping.put('â', 0xD2);
        marioPartyMapping.put('ç', 0xD4);
        marioPartyMapping.put('!', 0xC2);
        marioPartyMapping.put('?', 0xC3);
        marioPartyMapping.put('\'', 0x5C);
        marioPartyMapping.put('\"', 0x5B);
        marioPartyMapping.put('(', 0x5D);
        marioPartyMapping.put(')', 0x5E);
        marioPartyMapping.put('-', 0x3D);
        marioPartyMapping.put('°', 0xBE);
        for (char c = 'A'; c <= 'Z'; c++) {
            marioPartyMapping.put(c, (int) c);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            marioPartyMapping.put(c, (int) c);
        }
    }
}
