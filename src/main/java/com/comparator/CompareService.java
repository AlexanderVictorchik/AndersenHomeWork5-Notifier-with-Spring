package com.comparator;

import java.util.*;

public class CompareService {

    public static List<String> compare(List<String> listFromAccount, List<String> listFromCommand) {
        listFromAccount.retainAll(listFromCommand);
        listFromCommand.removeAll(listFromAccount);
        return listFromCommand;
    }
}