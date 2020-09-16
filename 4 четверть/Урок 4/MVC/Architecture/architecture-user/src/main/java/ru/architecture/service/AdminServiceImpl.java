package ru.architecture.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdminServiceImpl implements AdminService {

    @Override
    public List<String> readListFiles() {
        //Читаем все файлы из директории
        String startPath = "...";
        List<String> files = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(startPath))) {
            files = paths
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }

    @Override
    public String readLogs(String logName) {
        //Читаем лог конкретного файла
        StringBuilder sb = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(logName))) {
            sb.append(stream.map(String::toString));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    public String getVideo() {
        //Код на получение видео
        return "";
    }

    @Override
    public void reboot() {
        //Код на перезагрузку
    }
}
