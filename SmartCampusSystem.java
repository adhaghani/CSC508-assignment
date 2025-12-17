import java.util.Scanner;

public class SmartCampusSystem {
    private WalkInNode walkInHead, walkInTail;  // head and tail of walk-in queue
    private VIPNode vipHead, vipTail;           // head and tail of VIP guest list

    public SmartCampusSystem() {
        walkInHead = walkInTail = null;
        vipHead = vipTail = null;
    }

    // Append a walk-in participant to the queue (singly linked list)
    public void addWalkInParticipant(Participant p) {
        WalkInNode newNode = new WalkInNode(p);
        if (walkInHead == null) {
            // empty queue
            walkInHead = walkInTail = newNode;
        } else {
            walkInTail.next = newNode;
            walkInTail = newNode;
        }
    }

    // Find and remove a walk-in participant by ID; return removed Participant or null if not found
    public Participant cancelWalkInParticipant(String id) {
        if (walkInHead == null) {
            return null;
        }
        // Check head
        if (walkInHead.participant.getId().equals(id)) {
            Participant removed = walkInHead.participant;
            walkInHead = walkInHead.next;
            if (walkInHead == null) { // queue became empty
                walkInTail = null;
            }
            return removed;
        }
        // Search through list
        WalkInNode prev = walkInHead;
        WalkInNode curr = walkInHead.next;
        while (curr != null) {
            if (curr.participant.getId().equals(id)) {
                Participant removed = curr.participant;
                prev.next = curr.next;
                if (curr == walkInTail) {
                    // removed tail
                    walkInTail = prev;
                }
                return removed;
            }
            prev = curr;
            curr = curr.next;
        }
        return null; // not found
    }

    // Display all walk-in participants in queue order
    public void displayWalkInQueue() {
        System.out.println("| Walk-In Check-In Queue:");
        System.out.println("*=====================================*");
        WalkInNode curr = walkInHead;
        int index = 1;
        while (curr != null) {
            System.out.println("| " + index + ". " + curr.participant);
            index++;
            curr = curr.next;
        }
    }

    // Append a VIP guest to the list (doubly linked list)
    public void addVIPGuest(Participant p) {
        VIPNode newNode = new VIPNode(p);
        if (vipHead == null) {
            vipHead = vipTail = newNode;
        } else {
            vipTail.next = newNode;
            newNode.prev = vipTail;
            vipTail = newNode;
        }
    }

    // Find and remove a VIP guest by ID; return removed Participant or null if not found
    public Participant removeVIPGuest(String id) {
        if (vipHead == null) {
            return null;
        }
        // Check head
        if (vipHead.participant.getId().equals(id)) {
            Participant removed = vipHead.participant;
            if (vipHead == vipTail) {
                // only one node
                vipHead = vipTail = null;
            } else {
                vipHead = vipHead.next;
                vipHead.prev = null;
            }
            return removed;
        }
        // Search through list
        VIPNode curr = vipHead.next;
        while (curr != null) {
            if (curr.participant.getId().equals(id)) {
                Participant removed = curr.participant;
                if (curr == vipTail) {
                    vipTail = curr.prev;
                    vipTail.next = null;
                } else {
                    curr.prev.next = curr.next;
                    curr.next.prev = curr.prev;
                }
                return removed;
            }
            curr = curr.next;
        }
        return null; // not found
    }

    // Display VIP guest list; reverse=true prints from tail to head
    public void displayVIPGuestList(boolean reverse) {
        if (!reverse) {
            System.out.println("| VIP Guest List:");
            System.out.println("*=====================================*");
            System.out.println();
            VIPNode curr = vipHead;
            int index = 1;
            while (curr != null) {
                System.out.println("| " + index + ". " + curr.participant);
                index++;
                curr = curr.next;
            }
        } else {
            System.out.println("| VIP Guest List (Reverse):");
            System.out.println("*=====================================*");
            System.out.println();
            VIPNode curr = vipTail;
            int index = 1;
            while (curr != null) {
                System.out.println("| " + index + ". " + curr.participant);
                index++;
                curr = curr.prev;
            }
        }
    }

    // Main interactive menu
    public static void main(String[] args) {
        SmartCampusSystem system = new SmartCampusSystem();
        Scanner scanner = new Scanner(System.in);
        // Print header and menu once
        System.out.println("*=====================================*");
        System.out.println("SmartCampus Check-In System");
        System.out.println();
        System.out.println("CSC508");
        System.out.println("Data Structure");
        System.out.println("Semester 20254 : October 2025 – January 2026");
        System.out.println("Assignment 1");
        System.out.println();
        System.out.println("*=====================================*");
        System.out.println(" ");


        while (true) {
            // Clear screen (console)
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("*=====================================*");
            System.out.println("|                                     |");
            System.out.println("|    [1] Add Walk-In Participant.     |");
            System.out.println("|    [2] Cancel Walk-In Check-In.     |");
            System.out.println("|    [3] Display Walk-In Queue.       |");
            System.out.println("|    [4] Add VIP Guest                |");
            System.out.println("|    [5] Remove VIP Guest             |");
            System.out.println("|    [6] Display VIP Guest List       |");
            System.out.println("|    [7] Exit                         |");
            System.out.println("|                                     |");
            System.out.println("*=====================================*");
            System.out.print("| Select option: ");
  
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.println("*=====================================*");
            if (choice == 7) {
                System.out.println("Thank you for using SmartCampus!");
                break;
            }
            switch (choice) {
                case 1:
                    System.out.print("| Enter name  : ");
                    String name = scanner.nextLine();
                    System.out.print("| Enter ID    : ");
                    String id = scanner.nextLine();
                    System.out.print("| Enter booth : ");
                    String booth = scanner.nextLine();
                    Participant walkInParticipant = new Participant(name, id, booth);
                    system.addWalkInParticipant(walkInParticipant);
                    System.out.println("*=====================================*");
                    System.out.println("| Participant " + name + " added to Walk-In queue.");
                    System.out.println("*=====================================*");
                    break;
                case 2:
                    System.out.print("| Enter ID to cancel  : ");
                    String cancelId = scanner.nextLine();
                    Participant removedWalkIn = system.cancelWalkInParticipant(cancelId);
                    if (removedWalkIn != null) {
                        System.out.println("*=====================================*");
                        System.out.println("| Participant " + removedWalkIn.getName() +
                                           " removed from Walk-In queue.");
                        System.out.println("*=====================================*");
                    }
                    break;
                case 3:
                    system.displayWalkInQueue();
                    break;
                case 4:
                    System.out.print("| Enter name: ");
                    String vipName = scanner.nextLine();
                    System.out.print("| Enter ID: ");
                    String vipId = scanner.nextLine();
                    System.out.print("| Enter event: ");
                    String vipEvent = scanner.nextLine();
                    Participant vipParticipant = new Participant(vipName, vipId, vipEvent);
                    system.addVIPGuest(vipParticipant);
                    System.out.println("*=====================================*");
                    System.out.println("| VIP Guest " + vipName + " added.");
                    System.out.println("*=====================================*");
                    break;
                case 5:
                    System.out.print("| Enter ID to remove: ");
                    String removeId = scanner.nextLine();
                    Participant removedVIP = system.removeVIPGuest(removeId);
                    if (removedVIP != null) {
                        System.out.println("*=====================================*");
                        System.out.println("| VIP Guest " + removedVIP.getName() + " removed.");
                        System.out.println("*=====================================*");
                    }
                    break;
                case 6:
                    System.out.println("*=====================================*");
                    System.out.print("| Display in reverse? (yes/no): ");
                    
                    String resp = scanner.nextLine();
                    boolean rev = resp.equalsIgnoreCase("yes");
                    System.out.println("*=====================================*");
                    system.displayVIPGuestList(rev);
                    break;
                default:
                    System.out.println("| Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}