package assesement.pkg01.comparartor;

import assesement.pkg01.Subject;

import java.util.Comparator;
import java.util.Map;

public class SubjectNameComparator implements Comparator<String> {
    private final Map<String, Subject> subjectIdMapWithSubject;

    public SubjectNameComparator(Map<String, Subject> subjectIdMapWithSubject) {
        this.subjectIdMapWithSubject = subjectIdMapWithSubject;
    }

    @Override
    public int compare(String o1, String o2) {
        return subjectIdMapWithSubject.get(o1).getSubJectName()
                .compareTo(subjectIdMapWithSubject.get(o2).getSubJectName());
    }
}
