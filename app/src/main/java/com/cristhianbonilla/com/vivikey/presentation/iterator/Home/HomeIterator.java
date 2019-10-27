    package com.cristhianbonilla.com.vivikey.presentation.iterator.Home;
    import android.content.Context;
    import com.cristhianbonilla.com.vivikey.core.domain.Contact;
    import com.cristhianbonilla.com.vivikey.core.domain.ContactNumber;
    import com.cristhianbonilla.com.vivikey.core.domain.User;
    import com.cristhianbonilla.com.vivikey.presentation.iterator.BaseIterator;
    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.ValueEventListener;

    import java.util.ArrayList;
    import java.util.List;

    public class HomeIterator extends BaseIterator implements IHomeIterator {

        @Override
        public  List<Contact> getContactsFriendsFromFirebase(Context context, ArrayList<Contact> contacts, String myPhoneNumber) {


            initFirebase(context);

            List<Contact> myFriendsList = new ArrayList<>();
            List<ContactNumber> phones = new ArrayList<>();

            for (Contact contact: contacts) {
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("contactsByUser/"+contact.getPhoneNumber()+"/"+myPhoneNumber);
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getValue()!=null){

                            Contact friend = dataSnapshot.getValue(Contact.class);
                            phones.add(new ContactNumber(friend.getOwner(),dataSnapshot.getKey()));
                            myFriendsList.add(friend);


                        insertFriends(myPhoneNumber,phones,context);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                  }
                });
            }

            return  myFriendsList;

        }

        @Override
        public void insertContacts(User user, List<Contact> contacts, Context context) {
            initFirebase(context);
            databaseReference.getDatabase().getReference().getDatabase().getReference();
            for (Contact contact:contacts) {
                databaseReference.child("contactsByUser").child(user.getPhone()).child(contact.getPhoneNumber()).setValue(contact);
            }

        }

        @Override
        public void insertFriends(String myPhoneNumber, List<ContactNumber> contacts, Context context) {
            initFirebase(context);
            databaseReference.getDatabase().getReference().getDatabase().getReference();
            for (ContactNumber contact:contacts) {
                databaseReference.child("friendsByPhoneUsers").child(myPhoneNumber).child(contact.getPhoneNumber()).setValue(contact);
            }
        }
    }
