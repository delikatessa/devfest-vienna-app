package at.devfest.app.ui.home;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.FormatStyle;

import at.devfest.app.R;
import at.devfest.app.data.app.model.Session;
import at.devfest.app.ui.core.picasso.CircleTransformation;
import at.devfest.app.ui.core.recyclerview.BaseViewHolder;
import at.devfest.app.utils.App;
import butterknife.BindView;

import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import at.devfest.app.R;
import at.devfest.app.data.app.model.Session;
import at.devfest.app.ui.core.picasso.CircleTransformation;
import at.devfest.app.ui.core.recyclerview.BaseViewHolder;
import at.devfest.app.utils.App;
import com.squareup.picasso.Picasso;

import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.FormatStyle;

import butterknife.BindView;

public class HomeCurrentListEntry extends BaseViewHolder {

    @BindView(R.id.sessions_list_entry_photo) ImageView photo;
    @BindView(R.id.sessions_list_entry_title) TextView title;
    @BindView(R.id.sessions_list_entry_selected_state) ImageView selectedState;
    @BindView(R.id.sessions_list_entry_room) TextView room;
    @BindView(R.id.sessions_list_entry_description) TextView description;
    @BindView(R.id.sessions_list_entry_time_symbol) ImageView timeSymbol;
    @BindView(R.id.sessions_list_entry_time) TextView time;
    @BindView(R.id.current_list_layout) View layout;

    private final Picasso picasso;

    public HomeCurrentListEntry(ViewGroup parent, Picasso picasso) {
        super(parent, R.layout.current_list_entry);
        this.picasso = picasso;
    }

    public void bindSession(Session session, boolean isSelected, boolean showTime) {
        String photoUrl = App.getPhotoUrl(session);
        if (TextUtils.isEmpty(photoUrl)) {
            photo.setImageDrawable(null);
        } else {
            picasso.load(photoUrl).transform(new CircleTransformation()).into(photo);
        }

        title.setText(session.getTitle());
        description.setText(session.getDescription());

        String roomText = session.getRoom();
        if (TextUtils.isEmpty(roomText)) {
            selectedState.setVisibility(View.GONE);
            isSelected = false;
        }
        else {
            room.setText(roomText);
            int selectedRes = isSelected ? R.drawable.sessions_list_entry_selected : R.drawable.sessions_list_entry_default;
            // As of Android Support Library 23.3.0, support vector drawables can only be loaded
            // via app:srcCompat or setImageResource().
            // selectedState.setImageDrawable(ContextCompat.getDrawable(selectedState.getContext(), selectedRes));
            selectedState.setImageResource(selectedRes);
            selectedState.setVisibility(View.VISIBLE);
        }
        int selectedColor = isSelected ? R.color.secondary_background : R.color.background;
        layout.setBackgroundResource(selectedColor);


        if (showTime) {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
            String fromTime = session.getFromTime().format(timeFormatter);
            String toTime = session.getToTime().format(timeFormatter);
            time.setText(fromTime + " - " + toTime);
            timeSymbol.setVisibility(View.VISIBLE);
            time.setVisibility(View.VISIBLE);
        }
        else {
            timeSymbol.setVisibility(View.GONE);
            time.setVisibility(View.GONE);
        }
    }
}
