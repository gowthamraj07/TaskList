import SwiftUI
import shared  // Kotlin Multiplatform generated framework

class TaskViewModelWrapper: ObservableObject {
    private let viewModel = ViewModelProvider().getTaskViewModel()

    @Published var tasks: [Task] = []
    @Published var newTaskText: String = ""

    init() {
        viewModel.observeTasks { tasks in
            DispatchQueue.main.async {
                self.tasks = tasks as? [Task] ?? []
                print("iOS received tasks:")
                self.tasks.forEach { task in
                    print("ID: \(task.id), Title: \(task.title), Done: \(task.isDone)")
                }
            }
        }
    }

    func addTask() {
        print("Adding task \(newTaskText)...")
        guard !newTaskText.isEmpty else { return }
        viewModel.addTask(title: newTaskText)
        newTaskText = ""
    }

    func toggle(task: Task) {
        print("Toggle task \(task.title)...")
        viewModel.toggleTask(id: task.id)
    }

    func delete(task: Task) {
        print("Deleting task \(task.title)...")
        viewModel.deleteTask(id: task.id)
    }
}

struct ChecklistScreen: View {
    @StateObject private var model = TaskViewModelWrapper()

    var body: some View {
        NavigationView {
            VStack {
                List {

                    ForEach(model.tasks, id: \.id) { task in

                        HStack {
                            Button(action: {
                                model.toggle(task: task)
                            }) {
                                Image(systemName: task.isDone ? "checkmark.circle.fill" : "circle")
                                    .padding()
                            }
                            .contentShape(Rectangle())  // Constrains tap zone to the image and padding
                            .buttonStyle(.plain)

                            Text(task.title)
                                .frame(maxWidth: .infinity, alignment: .leading)

                            Button(action: {
                                model.delete(task: task)
                            }) {
                                Image(systemName: "trash")
                                    .padding()
                            }
                            .contentShape(Rectangle())  // Same here
                            .buttonStyle(.plain)
                        }
                    }

                }
                .listStyle(.plain)

                HStack {
                    TextField("Enter a task", text: $model.newTaskText)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                    Button("Add", action: model.addTask)
                }
                .padding()
            }
            .navigationTitle("📝 TaskLite")
        }
    }
}
