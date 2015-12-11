package nyc.c4q.android;

import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;

import java.util.List;

import nyc.c4q.android.model.Email;
import nyc.c4q.android.rest.FakeEmailService;
import nyc.c4q.android.ui.EmailDetailActivity;

public class EmailApplication extends Application {
  public static final int EMAIL_POLL_IN_SEC = 5;

  public static final int MILLIS_PER_SEC = 1000;
  public static final int DELAY_MILLIS = EMAIL_POLL_IN_SEC * MILLIS_PER_SEC;

  private static final FakeEmailService emailService = new FakeEmailService();

  private HandlerThread handlerThread;
  private NotificationManager notificationManager;
  private Runnable emailCheck;

  @Override public void onCreate() {
    super.onCreate();

    // TODO - finish this
    notificationManager = null;

    handlerThread = new HandlerThread("email-timer");
    handlerThread.start();
    Looper looper = handlerThread.getLooper();
    final Handler handler = new Handler(looper);

    emailCheck = new Runnable() {
      @Override public void run() {
        if (emailService.hasNewMail()) {

          // TODO


          // 1) get the most recent email and..
          // a) send a notification to the user notifying of the new email
          // b) use R.string.you_got_email as title
          // c) use R.string.notification_email_from (accounting for who sent the email)
          // d) when user clicks on notification, go to EmailDetailActivity

          List<Email> emails = emailService.getEmails();
          displayNotification(emails.get(0));
        }


        handler.postDelayed(emailCheck, DELAY_MILLIS);
      }
    };

    handler.postDelayed(emailCheck, DELAY_MILLIS);
  }

  public void displayNotification(Email email){
    Intent detailIntent = new Intent(this, EmailDetailActivity.class);

// Because clicking the notification opens a new ("special") activity, there's
// no need to create an artificial back stack.
    PendingIntent resultPendingIntent =
            PendingIntent.getActivity(
                    this,
                    0,
                    detailIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );


    NotificationCompat.Builder mBuilder =
            new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.c4q)
                    .setContentTitle("" + R.string.you_got_email)
                    .setContentText(R.string.notification_email_from + email.getFrom());
    mBuilder.setContentIntent(resultPendingIntent);
    mBuilder.build().notify();
  }
}
