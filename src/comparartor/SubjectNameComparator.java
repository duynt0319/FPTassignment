package comparartor;

import subject.Subject;

import java.util.Comparator;
import java.util.Map;

public class SubjectNameComparator implements Comparator<String> {
    private final Map<String, Subject> subjectIdMapWithSubject;

    public SubjectNameComparator(Map<String, Subject> subjectIdMapWithSubject) {
        this.subjectIdMapWithSubject = subjectIdMapWithSubject;
    }

    @Override
    public int compare(String key1, String key2) {
        return subjectIdMapWithSubject.get(key1).getSubJectName()
                .compareTo(subjectIdMapWithSubject.get(key2).getSubJectName());
    }
}
