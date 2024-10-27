import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Timer;

public class BackToFutureVisualizer extends JFrame {

    private static final int ANIMATION_DELAY = 4;
    private static final int POLL_INTERVAL = 500;
    private static final int MAX_PROGRESS = 100;

    private final Map<String, JProgressBar> noteBars;
    private int lastNotesCount = 0;

    public BackToFutureVisualizer() {
        setTitle("Back to the Future Sound Visualizer");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 8, 5, 5));
        getContentPane().setBackground(new Color(10, 10, 10)); // Dark black background for contrast

        noteBars = new HashMap<>();

        // Define the notes
        String[] notes = {"c", "d", "e", "f", "g", "a", "b", "c2"};
        for (String note : notes) {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setBackground(new Color(10, 10, 10));

            JProgressBar progressBar = new JProgressBar(JProgressBar.VERTICAL, 0, MAX_PROGRESS);
            progressBar.setForeground(new Color(255, 165, 0)); // Bright orange for progress fill
            progressBar.setBackground(new Color(30, 30, 30));  // Dark grey for a sleek look
            progressBar.setBorder(BorderFactory.createLineBorder(new Color(255, 69, 0), 2)); // Fiery red border to add energy

            JLabel label = new JLabel(note.toUpperCase(), SwingConstants.CENTER);
            label.setForeground(new Color(255, 140, 0)); // Golden orange for labels
            label.setFont(new Font("Arial", Font.BOLD, 12));

            panel.add(label, BorderLayout.NORTH);
            panel.add(progressBar, BorderLayout.CENTER);

            add(panel);
            noteBars.put(note, progressBar);
        }

        Timer pollApiTimer = new Timer(POLL_INTERVAL, e -> pollApiForNoteUpdates());
        pollApiTimer.start();
    }

    private void pollApiForNoteUpdates() {
        try {
            System.out.println("Polling API for updates...");

            URL url = new URL("http://localhost:8080/getArray");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            System.out.println("API Response: " + response.toString());

            String responseString = response.toString().replace("[", "").replace("]", "");
            String[] notesData = responseString.split("},");

            if (notesData.length > lastNotesCount) {
                lastNotesCount = notesData.length;

                String latestNoteData = notesData[notesData.length - 1]
                        .replace("{", "")
                        .replace("}", "")
                        .replace("\"", "");

                System.out.println("Latest Note Data: " + latestNoteData);

                String latestNote = "";
                for (String pair : latestNoteData.split(",")) {
                    String[] keyValue = pair.split(":");
                    if (keyValue.length == 2 && keyValue[0].trim().equals("note")) {
                        latestNote = keyValue[1].trim().toLowerCase();
                        break;
                    }
                }

                System.out.println("Extracted note (post-formatting): '" + latestNote + "'");
                System.out.println("Available keys in noteBars: " + noteBars.keySet());

                if (noteBars.containsKey(latestNote)) {
                    JProgressBar progressBar = noteBars.get(latestNote);
                    System.out.println("Animating progress bar for note: " + latestNote);
                    Timer animationTimer = new Timer(ANIMATION_DELAY, new AnimationAction(progressBar));
                    animationTimer.start();
                } else {
                    System.out.println("No progress bar found for note: '" + latestNote + "'");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private class AnimationAction implements ActionListener {
        private final JProgressBar progressBar;
        private int progress = 0;
        private boolean isFilling = true;

        public AnimationAction(JProgressBar progressBar) {
            this.progressBar = progressBar;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isFilling) {
                progress += 2;
                if (progress >= MAX_PROGRESS) {
                    isFilling = false;
                }
            } else {
                progress -= 2;
                if (progress <= 0) {
                    progress = 0;
                    ((Timer) e.getSource()).stop();
                }
            }
            progressBar.setValue(progress);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BackToFutureVisualizer app = new BackToFutureVisualizer();
            app.setVisible(true);

            JProgressBar testProgressBar = app.noteBars.get("c");
            if (testProgressBar != null) {
                Timer testAnimationTimer = new Timer(10, app.new AnimationAction(testProgressBar));
                testAnimationTimer.start();
            }
        });
    }
}
