package edu.csulb.android.projecthomies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactsPageListAdapter  extends ArrayAdapter<ContactsPageCard> {
    private static final String TAG = "CardArrayAdapter";
    private List<ContactsPageCard> contactsPageCardList = new ArrayList<ContactsPageCard>();

    static class CardViewHolder {
        TextView line1;
        TextView line2;
    }

    public ContactsPageListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public void add(ContactsPageCard object) {
        contactsPageCardList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.contactsPageCardList.size();
    }

    @Override
    public ContactsPageCard getItem(int index) {
        return this.contactsPageCardList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CardViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.activity_detailed_contact_view, parent, false);
            viewHolder = new CardViewHolder();
            viewHolder.line1 = (TextView) row.findViewById(R.id.line1);
            //viewHolder.line2 = (TextView) row.findViewById(R.id.line2);
            row.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder)row.getTag();
        }
        ContactsPageCard contactsPageCard = getItem(position);
        viewHolder.line1.setText(contactsPageCard.getLine1());
        viewHolder.line2.setText(contactsPageCard.getLine2());
        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
