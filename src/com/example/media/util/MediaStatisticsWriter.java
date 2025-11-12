package com.example.media.util;

import com.example.media.classes.Playlist;
import com.example.media.classes.Track;
import com.example.media.classes.Video;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * Клас-утиліта для запису статистики у вихідні файли.
 *
 * Студенти мають реалізувати методи нижче з використанням:
 * - Stream API
 * - лямбда-виразів
 * - method references
 *
 * Формат вихідних файлів має бути простим і "людиночитабельним"
 * (див. приклади у README.md).
 */
public class MediaStatisticsWriter {

    /**
     * Запис статистики по музичних треках у файл.
     *
     * Очікуваний формат файлу `tracks_output.txt`:
     * --------------------------------------------
     * Tracks count: <загальна кількість треків>
     * Average duration: <середня тривалість у секундах> seconds
     *
     * Top 3 tracks by rating:
     * 1. <назва треку> (rating <рейтинг>)
     * 2. <назва треку> (rating <рейтинг>)
     * 3. <назва треку> (rating <рейтинг>)
     *
     * Pop tracks:
     * - <назва треку>
     * - <назва треку>
     * --------------------------------------------
     *
     * Пояснення:
     * - Tracks count → кількість елементів у плейлисті
     * - Average duration → середня тривалість у секундах
     * - Top 3 → відсортовані за рейтингом спаданням,
     *   при однаковому рейтингу брати найдовші
     * - Pop tracks → усі треки, у яких жанр == "Pop"
     */
    public static void writeTrackStats(Playlist<Track> playlist, String filename) throws IOException {
        // TODO: Реалізуйте цей метод
        // Підказки:
        // - Використайте playlist.getItems().size() для підрахунку кількості
        List<Track> tracks = playlist.getItems();
        int totalTracks = tracks.size();

        // - Використайте stream().mapToInt(Track::getDuration).average() для середньої тривалості
        OptionalDouble averageDuration = tracks.stream()
                .mapToInt(Track::getDuration)
                .average();

        // - Використайте stream().sorted(...).limit(3) для топ-3 за рейтингом
        List<Track> topTracks = tracks.stream()
                .sorted((track1, track2) -> Integer.compare(track2.getRating(), track1.getRating()))
                .limit(3)
                .collect(Collectors.toList());

        // - Використайте stream().filter(t -> t.getGenre().equalsIgnoreCase("Pop")) для відбору Pop-треків
        List<Track> popTracks = tracks.stream()
                .filter(track -> track.getGenre().equalsIgnoreCase("Pop"))
                .collect(Collectors.toList());

        // - Запишіть результати у файл через PrintWriter або Files.write()
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println("Tracks count: " + totalTracks);
            writer.printf("Average duration: " + averageDuration.getAsDouble() + " seconds\n");
            writer.println("\nTop 3 tracks by rating:");
            for (int i = 0; i < 3; i++) {
                Track track = topTracks.get(i);
                writer.print(i+1 + ". " + track.getTitle() + " (rating: " + track.getRating() + ") " + "\n");
            }
            writer.println("\nPop tracks:");
            for (Track track : popTracks) {
                writer.println("– " + track.getTitle());
            }
        }
    }

    /**
     * Запис статистики по відео у файл.
     *
     * Очікуваний формат файлу `videos_output.txt`:
     * --------------------------------------------
     * Videos count: <загальна кількість відео>
     * Average duration: <середня тривалість у секундах> seconds
     *
     * Top 3 videos by views:
     * 1. <назва відео> (<кількість переглядів> views)
     * 2. <назва відео> (<кількість переглядів> views)
     * 3. <назва відео> (<кількість переглядів> views)
     *
     * Education videos:
     * - <назва відео>
     * - <назва відео>
     * --------------------------------------------
     *
     * Пояснення:
     * - Videos count → кількість елементів у плейлисті
     * - Average duration → середня тривалість у секундах
     * - Top 3 → відсортовані за views спаданням
     * - Education videos → усі відео, у яких category == "Education"
     */
    public static void writeVideoStats(Playlist<Video> playlist, String filename) throws IOException {
        // TODO: Реалізуйте цей метод
        // Підказки:
        // - Використайте playlist.getItems().size() для підрахунку кількості
        List<Video> videos = playlist.getItems();
        int totalVideos = videos.size();

        // - Використайте stream().mapToInt(Video::getDuration).average() для середньої тривалості
        OptionalDouble averageDuration = videos.stream()
                .mapToInt(Video::getDuration)
                .average();

        // - Використайте stream().sorted(...).limit(3) для топ-3 за views
        List<Video> topVideos = videos.stream()
                .sorted((video1, video2) -> Integer.compare(video2.getViews(), video1.getViews()))
                .limit(3)
                .collect(Collectors.toList());

        // - Використайте stream().filter(v -> v.getCategory().equalsIgnoreCase("Education")) для Education-відео
        List<Video> educationVideos = videos.stream()
                .filter(video -> video.getCategory().equalsIgnoreCase("Education"))
                .collect(Collectors.toList());

        // - Запишіть результати у файл через PrintWriter або Files.write()
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println("Videos count: " + totalVideos);
            writer.printf("Average duration: " + averageDuration.getAsDouble() + " seconds\n");
            writer.println("\nTop 3 video by views:");
            for (int i = 0; i < 3; i++) {
                Video video = topVideos.get(i);
                writer.print(i+1 + ". " + video.getTitle() + " (" + video.getViews() + " views) " + "\n");
            }
            writer.println("\nEducation videos:");
            for (Video video : educationVideos) {
                writer.println("– " + video.getTitle());
            }
        }
    }
}
