package fr.royalprog.royalsui.config;

import com.mojang.datafixers.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ModConfigProvider implements SimpleConfig.DefaultConfig {

    private String configContents = "";

    public List<Pair> getConfigsList() {
        return configsList;
    }

    private final List<Pair> configsList = new ArrayList<>();

    public void addKeyValuePair(Pair<String, ?> keyValuePair, String comment) {
        configsList.add(keyValuePair);
        configContents += keyValuePair.getFirst() + "=" + keyValuePair.getSecond() + " #"
                + comment + " | default: " + keyValuePair.getSecond() + "\n";
    }

    public void modifyKeyValue(Pair<String, ?> keyPair) {
        String[] temp = configContents.split("\n");
        int i;
        int j = 0;
        for (i = 0; i < temp.length; i++) {
            if (temp[i].contains(keyPair.getFirst()))
                break ;
        }

        for (Pair pair : configsList){
            if (pair.getFirst().equals(keyPair.getFirst()))
                break;
            j++;
        }

        if (i == temp.length || j == configsList.size())
            return ;

        System.out.println("configsList.get(j).getSecond().toString() : " + configsList.get(j).getSecond().toString());
        System.out.println("keyPair.getSecond().toString() : " + keyPair.getSecond().toString());
        temp[i] =  temp[i].replace(configsList.get(j).getSecond().toString(), keyPair.getSecond().toString());
        StringBuilder newContent = new StringBuilder();
        for (String s : temp)
            newContent.append(s).append("\n");

        configsList.set(j, keyPair);
        configContents = newContent.toString();

    }

    @Override
    public String get(String namespace) {
        return configContents;
    }
}
