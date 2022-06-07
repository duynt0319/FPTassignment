package comparartor;

import java.util.Comparator;
import java.util.Map;

public class StudentNameComparator implements Comparator<String> {

    private final Map<String, String> subjectIdMapWithSubject;

    public StudentNameComparator(Map<String, String> subjectIdMapWithSubject) {
        this.subjectIdMapWithSubject = subjectIdMapWithSubject;
    }

    @Override
    public int compare(String key1, String key2) {
        return subjectIdMapWithSubject.get(key1).compareTo(subjectIdMapWithSubject.get(key2));
    }
}
