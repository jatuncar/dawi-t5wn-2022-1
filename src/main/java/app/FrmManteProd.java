package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Producto;
import model.Proveedor;
//import model.Producto;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;
	
	private JTextArea txtSalida;
	private JTextField txtC�digo;
	private JComboBox cboCategorias;
	private JComboBox cboProveedor;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);
		
		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);
		
		txtC�digo = new JTextField();
		txtC�digo.setBounds(122, 11, 86, 20);
		contentPane.add(txtC�digo);
		txtC�digo.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);
		
		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 70, 100, 22);
		contentPane.add(cboCategorias);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);
		
		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);
		
		JLabel lblProveedor = new JLabel("Proveedor:");
		lblProveedor.setBounds(220, 106, 102, 14);
		contentPane.add(lblProveedor);
		
		cboProveedor = new JComboBox();
		cboProveedor.setBounds(320, 106, 100, 22);
		contentPane.add(cboProveedor);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarProducto();
			}
		});
		btnBuscar.setBounds(324, 54, 89, 23);
		contentPane.add(btnBuscar);
		
		llenaCombo();
	}

	void buscarProducto() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();

		Producto p = em.find(Producto.class, txtC�digo.getText());
		
		if (p == null)
			txtSalida.setText("ID no existe");
		else {
			txtDescripcion.setText(p.getDes_prod());
		}

		em.close();
	}
	
	void llenaCombo() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();

		TypedQuery<Categoria> consulta = em.
				createQuery("select c from Categoria c", Categoria.class);
		
		List<Categoria> lstCategorias = consulta.getResultList();
		cboCategorias.addItem("Seleccione...");
		for (Categoria c : lstCategorias) {
			cboCategorias.addItem(c.getDescripcion());
		}
		
		TypedQuery<Proveedor> consulta2 = em.
				createQuery("select c from Proveedor c", Proveedor.class);
		
		List<Proveedor> lstProveedores = consulta2.getResultList();
		cboProveedor.addItem("Seleccione...");
		for (Proveedor p : lstProveedores) {
			cboProveedor.addItem(p.getNombre_rs());
		}
		em.close();
	}
	
	void listado() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();

		TypedQuery<Producto> consulta = em.
				createQuery("select c from Producto c", Producto.class);
		List<Producto> lstProductos = consulta.getResultList();
		
		txtSalida.setText("");
		txtSalida.append("******************************************\n");
		for (Producto p : lstProductos) {
			txtSalida.append("Id Prod.......: " + p.getId_prod() + "\n");
			txtSalida.append("Descripci�n...: " + p.getDes_prod() + "\n");
			txtSalida.append("Stock.........: " + p.getStk_prod() + "\n");
			txtSalida.append("Precio........: " + p.getPre_prod() + "\n");
			txtSalida.append("ID.Categoria..: " + p.getIdcategoria() + "\t" +
			"Categoria..: " + p.getCategoria().getDescripcion() + "\n");
			txtSalida.append("Estado........: " + p.getEst_prod() + "\n");
			txtSalida.append("ID.Proveedor..: " + p.getIdprovedor() + "\t" +
			"Proveedor.....: " + p.getProveedor().getNombre_rs() + "\n");
			txtSalida.append("******************************************\n");
		}
		
		
		em.close();
	}
	
	void registrar() {
		String idprod = txtC�digo.getText();  // TAREA, Completar los campos de la GUI
		String descripcion = "Mascarilla KN 95";
		int stock = 100;
		double precio = 2.5;
		int categoria = cboCategorias.getSelectedIndex();
		int estado = 1; // fijo
		int proveedor = cboProveedor.getSelectedIndex();

		Producto p = new Producto();
		p.setId_prod(idprod);
		p.setDes_prod(descripcion);
		p.setStk_prod(stock);
		p.setPre_prod(precio);
		p.setIdcategoria(categoria);
		p.setEst_prod(estado);
		p.setIdprovedor(proveedor);
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();

		em.getTransaction().begin();
		em.persist(p); 
		em.getTransaction().commit();
		
		em.close();
		JOptionPane.showMessageDialog(this, "Producto agregado");

	}
}
