import socket

server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_address = ('localhost', 12345)
server_socket.bind(server_address)
server_socket.listen(1)

client_socket, client_address = server_socket.accept()

data = client_socket.recv(1024).decode('utf-8')
print("Received from client:", data)

response = "Server received your message: " + data
client_socket.send(response.encode('utf-8'))

client_socket.close()
server_socket.close()
