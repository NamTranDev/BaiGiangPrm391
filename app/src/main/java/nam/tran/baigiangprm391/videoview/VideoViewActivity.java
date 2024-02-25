package nam.tran.baigiangprm391.videoview;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import nam.tran.baigiangprm391.R;

public class VideoViewActivity extends AppCompatActivity {

    private static final String TAG = VideoViewActivity.class.getName();
    private VideoView videoView;
    private int position = 0;
    private MediaController mediaController;

    // Phát 1 video trong thư mục RAW.
    public static void playRawVideo(Context context, VideoView videoView, int resVideoId) {
        try {
            Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + resVideoId);
            Log.i(TAG, "Video URI: " + uri);
            videoView.setVideoURI(uri);
            videoView.requestFocus();

        } catch (Exception e) {
            Log.e(TAG, "Error Play Raw Video: " + e.getMessage());
            Toast.makeText(context, "Error Play Raw Video: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    //Phat video online
    public static void playURLVideo(Context context, VideoView videoView, String videoURL) {
        try {
            Log.i(TAG, "Video URL: " + videoURL);

            Uri uri = Uri.parse(videoURL);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
        } catch (Exception e) {
            Log.e(TAG, "Error Play URL Video: " + e.getMessage());
            Toast.makeText(context, "Error Play URL Video: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        this.videoView = findViewById(R.id.videoView);
        Button buttonURL = findViewById(R.id.button_url);
        Button buttonRaw = findViewById(R.id.button_raw);

        // Thiết lập trình điều khiển Media
        if (this.mediaController == null) {
            this.mediaController = new MediaController(this);

            //Thiết lập video view vào trong trình điều khiển
            this.mediaController.setAnchorView(videoView);

            // Thiết lập trình điều khiển cho video View
            this.videoView.setMediaController(mediaController);
        }


        // Khi video view đã sẵn sàng phát
        this.videoView.setOnPreparedListener(mediaPlayer -> {
            videoView.seekTo(position);
            if (position == 0) {
                videoView.start();
            }
            // Quy định thay đổi kích thước cho VideoView khi màn hình thay đổi (dọc-ngang)
            mediaPlayer.setOnVideoSizeChangedListener((mp, width, height) -> {
                mediaController.setAnchorView(videoView);
            });
        });

        //Phát video trong thư mục raw
        buttonRaw.setOnClickListener(v -> {
            playRawVideo(VideoViewActivity.this, videoView, R.raw.myvideo);
        });

        //Phát video online
        buttonURL.setOnClickListener(v -> {
            String videoURL = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4";
            playURLVideo(VideoViewActivity.this, videoView, videoURL);
        });
    }

    //Khi có sự thay đổi giao diện, trạng thái thì dừng video
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("CurrentPosition", videoView.getCurrentPosition());
        videoView.pause();
    }

    // Khôi phục video
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        position = savedInstanceState.getInt("CurrentPosition");
        videoView.seekTo(position);
    }
}
