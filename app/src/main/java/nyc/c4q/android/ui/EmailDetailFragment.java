package nyc.c4q.android.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import nyc.c4q.android.R;
import nyc.c4q.android.model.Email;
import nyc.c4q.android.rest.FakeEmailService;

public class EmailDetailFragment extends Fragment {
  private Email email;
  private static final DateFormat formatter = SimpleDateFormat.getDateInstance(DateFormat.SHORT);

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    email = (Email) getArguments().getSerializable("email");
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_email_detail, container, false);
    ImageView imgView = (ImageView) view.findViewById(R.id.email_from_img);
    TextView tvfrom = (TextView) view.findViewById(R.id.email_from);
    TextView tvEmailSubj = (TextView) view.findViewById(R.id.email_subject);
    TextView tvBody = (TextView) view.findViewById(R.id.email_body);
    TextView tvEmailSent = (TextView) view.findViewById(R.id.email_sent);

    // TODO - replace nulls
    Picasso.with(getActivity()).load((FakeEmailService.RANDOM_PIC)).into((ImageView) imgView);

    tvBody.setText(email.getBody());

    // TODO - bind email data to views

    return view;
  }

  public static EmailDetailFragment newInstance(Email email) {

    // create fragment, set up its only argument (the email) and return it

    // hint
    //args.putSerializable("email", email);
    Bundle args = new Bundle();
    args.putSerializable("email", email);
    return new EmailDetailFragment();
  }
}
