package by.ealipatov.mynotesapp.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import by.ealipatov.mynotesapp.R;
import by.ealipatov.mynotesapp.domain.Callback;
import by.ealipatov.mynotesapp.domain.InMemoryNotesRepository;
import by.ealipatov.mynotesapp.domain.Note;

public class NotesListFragment extends Fragment {

    public static final String NOTES_CLICKED_KEY = "NOTES_CLICKED_KEY";
    public static final String SELECTED_NOTES = "SELECTED_NOTES";
    public NoteDetailsFragment noteDetailsFragment;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        if (requireActivity() instanceof ToolbarHolder) {
            ((ToolbarHolder) requireActivity()).setToolbar(toolbar);
        }

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        Toast.makeText(requireContext(), R.string.find, Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_sort:
                        Toast.makeText(requireContext(), R.string.sort, Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_close:
                        new AlertDialog.Builder(requireContext())
                                .setTitle(R.string.app_name)
                                .setMessage(R.string.app_close)
                                .setIcon(R.drawable.ic_baseline_clear_24)
                                .setCancelable(false)
                                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        getActivity().finish();
                                    }
                                })
                                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(requireContext(), R.string.staying, Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
                        return true;
                }
                return false;
            }
        });

        view.findViewById(R.id.add_new_note).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new AddNewNoteFragment())
                        .addToBackStack("new_note")
                        .commit();
            }
        });

        RecyclerView notesList = view.findViewById(R.id.notes_list);
        notesList.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

        NotesAdaptor adaptor = new NotesAdaptor(this);
        adaptor.setNoteClicked(new OnNoteClicked() {
            @Override
            public void onNoteClicked(Note notes) {
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

                    Bundle bundle = new Bundle();
                    bundle.putParcelable(SELECTED_NOTES, notes);
                    getParentFragmentManager()
                            .setFragmentResult(NOTES_CLICKED_KEY, bundle);

                } else {
                    NoteDetailsFragment noteDetailsFragment = NoteDetailsFragment.newInstance(notes);
                    getParentFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, noteDetailsFragment)
                            .addToBackStack("detail_notes")
                            .commit();
                }

            }
        });

        notesList.setAdapter(adaptor);

        ProgressBar progressBar = view.findViewById(R.id.progress_bar);

        progressBar.setVisibility(View.VISIBLE);

        InMemoryNotesRepository.getInstance(requireContext()).getAll(new Callback<List<Note>>() {
            @Override
            public void onSuccess(List<Note> data) {

                adaptor.setDataNotes(data);

                adaptor.notifyDataSetChanged();

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable exception) {

                progressBar.setVisibility(View.GONE);

            }
        });
/*
        getParentFragmentManager().
                setFragmentResultListener(AddNewNoteFragment.KEY_RESULT, getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Note note = result.getParcelable(AddNewNoteFragment.ARG_NOTE);
                        int index = adaptor.addNote(note);
                        adaptor.notifyItemInserted(index);

                        notesList.smoothScrollToPosition(index);
                    }
                });
*/

    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = requireActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_notes_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_edit:
                Toast.makeText(requireContext(),"edit", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_delete:
                Toast.makeText(requireContext(),"delete", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onContextItemSelected(item);
    }
}
