public class Lab5D {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        String t1 = in.nextLine();
        String t2 = in.nextLine();
        StringBuilder sb = new StringBuilder();
        int t2Length = t2.length();
        for (int i = t2Length - 1; i >= 0; --i) {
            char current = t2.charAt(i);
            switch (current) {
                case 'W':
                    sb.append('E');
                    break;
                case 'S':
                    sb.append('N');
                    break;
                case 'N':
                    sb.append('S');
                    break;
                case 'E':
                    sb.append('W');
                    break;
            }
        }
        sb.append(t1);
        String joint = sb.toString();
        String suffix = joint.substring(1);
        int suffixLength = suffix.length();
        int[] next = new int[suffixLength];
        if (joint.charAt(0) == suffix.charAt(0)) {
            // for String, StringBuilder and
            // StringBuffer, charAt is constant time complexity
            next[0] = 1;
        } else {
            next[0] = 0;
        }
        for (int i = 1; i < suffixLength; i++) {
            if (joint.charAt(next[i - 1]) == suffix.charAt(i)) {
                next[i] = next[i - 1] + 1;
            } else {
                next[i] = 0;
            }
        }
        if (next[suffixLength - 1] != 0) {
            out.println("NO");
        } else {
            out.println("YES");
        }
        out.close();
    }
}
