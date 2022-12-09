import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static boolean logEntry = true;
    public static void folderCreation(File checkName, File newName, StringBuilder sBuilder) {
        if (checkName.exists()) {
            if (!newName.exists()) {
                if (newName.mkdir()) {
                    sBuilder.append("Каталог ").append(newName.getName()).append(" создан\n");
                    System.out.printf("Каталог \"%s\" создан\n", newName.getName());
                } else {
                    sBuilder.append("Каталог ").append(newName.getName()).append(" не удалось создать\n");
                    System.out.printf("Не удалось создать каталог \"%s\"\n", newName.getName());
                }
            } else {
                logEntry = false;
                System.out.printf("Каталог \"%s\" уже существует\n", newName.getName());
            }
        } else {
            System.out.printf("Каталог \"%s\" не найден\n", checkName.getName());
        }
    }

    public static void fileCreation(File checkName, File newName, StringBuilder sBuilder) {
        if (checkName.exists()) {
            try {
                if (!newName.exists()) {
                    if (newName.createNewFile()) {
                        sBuilder.append("Файл ").append(newName.getName()).append(" создан\n");
                        System.out.printf("Файл \"%s\" создан\n", newName.getName());
                    } else {
                        sBuilder.append("Файл ").append(newName.getName()).append(" не удалось создать\n");
                        System.out.printf("Не удалось создать файл \"%s\"\n", newName.getName());
                    }
                } else {
                    logEntry = false;
                    System.out.printf("Файл \"%s\" уже существует\n", newName.getName());
                }
            } catch (IOException ex) {
                sBuilder.append("Файл ").append(newName.getName()).append(" не был создан\n");
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.printf("Каталог \"%s\" не найден\n", checkName.getName());
        }
    }

    public static void main(String[] args) {
        StringBuilder sBuilder = new StringBuilder();

        File Games = new File("C:\\User\\Games");
        File src = new File(Games + "\\src");
        File res = new File(Games + "\\res");
        File savegames = new File(Games + "\\savegames");
        File temp = new File(Games + "\\temp");
        File main = new File(src + "\\main");
        File test = new File(src + "\\test");
        File drawables = new File(res + "\\drawables");
        File vectors = new File(res + "\\vectors");
        File icons = new File(res + "\\icons");

        File mainJava = new File(main + "\\Main.java");
        File utilsJava = new File(main + "\\Utils.java");
        File tempTxt = new File(temp + "\\temp.txt");

        /* Создание в папке Games четырёх директорий: src, res, savegames, temp */
        folderCreation(Games, src, sBuilder);
        folderCreation(Games, res, sBuilder);
        folderCreation(Games, savegames, sBuilder);
        folderCreation(Games, temp, sBuilder);

        /* Создание в каталоге src двух директорий: main, test */
        folderCreation(src, main, sBuilder);
        folderCreation(src, test, sBuilder);

        /* Создание в подкаталоге main двух файлов: Main.java, Utils.java */
        fileCreation(main, mainJava, sBuilder);
        fileCreation(main, utilsJava, sBuilder);

        /* Создание в каталоге res трёх директорий: drawables, vectors, icons */
        folderCreation(res, drawables, sBuilder);
        folderCreation(res, vectors, sBuilder);
        folderCreation(res, icons, sBuilder);

        /* Создание В директории temp файла temp.txt */
        fileCreation(temp, tempTxt, sBuilder);

        /* Заполнение файла temp.txt информацией об успешном или неуспешном создании файлов и директорий*/
        if (logEntry) {
            try (FileOutputStream fos = new FileOutputStream(tempTxt)) {
                byte[] bytes = sBuilder.toString().getBytes();
                fos.write(bytes, 0, bytes.length);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
