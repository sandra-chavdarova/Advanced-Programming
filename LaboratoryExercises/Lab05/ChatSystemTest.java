package LaboratoryExercises.Lab05;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.TreeSet;

class NoSuchRoomException extends Exception {
    String roomName;

    public NoSuchRoomException(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String getMessage() {
        return "NoSuchRoomException " + roomName;
    }
}

class NoSuchUserException extends Exception {
    String username;

    public NoSuchUserException(String username) {
        this.username = username;
    }

    @Override
    public String getMessage() {
        return "NoSuchUserException " + username;
    }
}

class ChatRoom {
    Set<String> users;
    String name;

    public ChatRoom(String name) {
        this.name = name;
        this.users = new TreeSet<>();
    }

    public void addUser(String username) {
        users.add(username);
    }

    public void removeUser(String username) {
        users.removeIf(user -> user.equalsIgnoreCase(username));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n");
        if (users.isEmpty()) {
            sb.append("EMPTY\n");
        } else {
            for (String user : users) {
                sb.append(user).append("\n");
            }
        }
        return sb.toString();
    }

    public boolean hasUser(String username) {
        for (String user : users)
            if (user.equalsIgnoreCase(username))
                return true;
        return false;
    }

    public int numUsers() {
        return users.size();
    }
}

class ChatSystem {
    Map<String, ChatRoom> rooms;
    Set<String> registeredUsers;

    public ChatSystem() {
        this.rooms = new TreeMap<>();
        this.registeredUsers = new TreeSet<>();
    }

    public void addRoom(String roomName) {
        rooms.putIfAbsent(roomName, new ChatRoom(roomName));
    }

    public void removeRoom(String roomName) {
        rooms.remove(roomName);
    }

    public ChatRoom getRoom(String roomName) throws NoSuchRoomException {
        ChatRoom room = rooms.get(roomName);
        if (room != null)
            return room;
        throw new NoSuchRoomException(roomName);
    }

    public void register(String username) {
        registeredUsers.add(username);
        ChatRoom minRoom = rooms.values().stream().min(Comparator.comparingInt(ChatRoom::numUsers)).orElse(null);
        if (minRoom != null) {
            minRoom.addUser(username);
        }
    }

    public void registerAndJoin(String username, String roomName) {
        registeredUsers.add(username);
        rooms.get(roomName).addUser(username);
    }

    public void joinRoom(String username, String roomName) throws NoSuchUserException, NoSuchRoomException {
        if (registeredUsers.contains(username)) {
            if (rooms.containsKey(roomName)) {
                rooms.get(roomName).addUser(username);
            } else
                throw new NoSuchRoomException(roomName);
        } else
            throw new NoSuchUserException(username);
    }

    public void leaveRoom(String username, String roomName) throws NoSuchUserException, NoSuchRoomException {
        if (registeredUsers.contains(username)) {
            if (rooms.containsKey(roomName)) {
                rooms.get(roomName).removeUser(username);
            } else
                throw new NoSuchRoomException(roomName);
        } else
            throw new NoSuchUserException(username);
    }

    public void followFriend(String username, String friend_username) throws NoSuchUserException {
        if (!registeredUsers.contains(friend_username))
            throw new NoSuchUserException(friend_username);
        if (!registeredUsers.contains(username)) {
            throw new NoSuchUserException(username);
        }

        for (ChatRoom room : rooms.values()) {
            if (room.hasUser(friend_username))
                room.addUser(username);
        }
    }
}

public class ChatSystemTest {
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchRoomException {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if (k == 0) {
            ChatRoom cr = new ChatRoom(jin.next());
            int n = jin.nextInt();
            for (int i = 0; i < n; ++i) {
                k = jin.nextInt();
                if (k == 0) cr.addUser(jin.next());
                if (k == 1) cr.removeUser(jin.next());
                if (k == 2) System.out.println(cr.hasUser(jin.next()));
            }
            System.out.println(cr.toString());
            n = jin.nextInt();
            if (n == 0) return;
            ChatRoom cr2 = new ChatRoom(jin.next());
            for (int i = 0; i < n; ++i) {
                k = jin.nextInt();
                if (k == 0) cr2.addUser(jin.next());
                if (k == 1) cr2.removeUser(jin.next());
                if (k == 2) cr2.hasUser(jin.next());
            }
            System.out.println(cr2.toString());
        }
        if (k == 1) {
            ChatSystem cs = new ChatSystem();
            Method mts[] = cs.getClass().getMethods();
            while (true) {
                String cmd = jin.next();
                if (cmd.equals("stop")) break;
                if (cmd.equals("print")) {
                    System.out.println(cs.getRoom(jin.next()) + "\n");
                    continue;
                }

                for (Method m : mts) {
                    try {
                        if (m.getName().equals(cmd)) {
                            String params[] = new String[m.getParameterTypes().length];
                            for (int i = 0; i < params.length; ++i) params[i] = jin.next();
                            m.invoke(cs, (Object[]) params);
                        }
                    } catch (Exception e) {
                        e.getMessage();
                    }
                }
            }
        }
    }
}
