import java.io.*;

public class CipherProcessor {

    public String encrypt(String input, char key) {
        StringWriter stringWriter = new StringWriter();
        try (CipherFilterWriter filterWriter = new CipherFilterWriter(stringWriter, key)) {
            for (int i = 0; i < input.length(); i++) {
                filterWriter.write(input.charAt(i));
            }
            filterWriter.flush();
        } catch (IOException e) {
            System.out.println("Помилка при шифруванні: " + e.getMessage());
        }
        return stringWriter.toString();
    }

    public String decrypt(String input, char key) {
        StringReader stringReader = new StringReader(input);
        StringWriter stringWriter = new StringWriter();
        try (CipherFilterReader filterReader = new CipherFilterReader(stringReader, key);
             CipherFilterWriter filterWriter = new CipherFilterWriter(stringWriter, (char) 0)) {

            int c;
            while ((c = filterReader.read()) != -1) {
                filterWriter.write(c);
            }
            filterWriter.flush();
        } catch (IOException e) {
            System.out.println("Помилка при дешифруванні: " + e.getMessage());
        }
        return stringWriter.toString();
    }

    static class CipherFilterWriter extends FilterWriter {

        private final int keyCode;

        public CipherFilterWriter(Writer out, char key) {
            super(out);
            this.keyCode = key;
        }

        @Override
        public void write(int c) throws IOException {
            // Шифруємо байт (символ)
            super.write(c + keyCode);
        }
    }

    static class CipherFilterReader extends FilterReader {

        private final int keyCode;

        public CipherFilterReader(Reader in, char key) {
            super(in);
            this.keyCode = key;
        }

        @Override
        public int read() throws IOException {
            int c = super.read();
            if (c != -1) {
                c -= keyCode;
            }
            return c;
        }
    }
}
