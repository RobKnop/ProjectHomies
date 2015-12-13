package edu.csulb.android.projecthomies.contacs;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.csulb.android.projecthomies.R;

// ADAPTER CLASS FOR CONTACTS
public class ContactsPageListAdapter extends RecyclerView.Adapter<ContactsPageListAdapter.PersonViewHolder> {

    private static ClickListener clickListener;

    public static class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CardView cv;
        TextView personName;
        TextView personCompany;
        ImageView personPhoto;
        private ClickListener mListener;


        PersonViewHolder(View itemView, ClickListener listener) {
            super(itemView);

            this.mListener = listener;
            itemView.setOnClickListener(this);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personCompany = (TextView)itemView.findViewById(R.id.person_company);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                clickListener.onItemClick(getAdapterPosition(), v);
            }
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ContactsPageListAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    List<ContactsPageCardData> persons;

    public ContactsPageListAdapter(List<ContactsPageCardData> persons){
        this.persons = persons;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_contacts_page_card_item, viewGroup, false);
        return new PersonViewHolder(v, clickListener);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(persons.get(i).getName());
        personViewHolder.personCompany.setText(persons.get(i).getCompany());
//        personViewHolder.personPhoto.setImageResource(persons.get(i).getImageID());
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}
