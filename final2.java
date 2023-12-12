package finalprog2;

import java.util.*;

class Manager {
    private String dni;
    private String nombre;
    private String legajo;

    public Manager(String dni, String nombre, String legajo) {
        this.dni = dni;
        this.nombre = nombre;
        this.legajo = legajo;
    }

    // Getters

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLegajo() {
        return legajo;
    }
}

class Deuda {
    private String codigo;
    private double importe;
    private Date fechaCalculo;

    public Deuda(String codigo, double importe, Date fechaCalculo) {
        this.codigo = codigo;
        this.importe = importe;
        this.fechaCalculo = fechaCalculo;
    }

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public Date getFechaCalculo() {
		return fechaCalculo;
	}

	public void setFechaCalculo(Date fechaCalculo) {
		this.fechaCalculo = fechaCalculo;
	}

   
}

class EstudioCobranzas {
    private String nombreEstudio;
    private Map<String, Manager> managers;
    private Map<String, List<Deuda>> deudasPorManager;

    public EstudioCobranzas(String nombreEstudio) {
        this.nombreEstudio = nombreEstudio;
        this.managers = new HashMap<>();
        this.deudasPorManager = new HashMap<>();
    }

    public void cargarManager(Manager manager) {
        if (manager != null && !managers.containsKey(manager.getDni())) {
            managers.put(manager.getDni(), manager);
            deudasPorManager.put(manager.getDni(), new ArrayList<>());
        } else {
            System.out.println("Error: Manager nulo o ya existente en el sistema.");
        }
    }
    
    public boolean asignarDeuda(String dniManager, Deuda deuda) {
        if (managers.containsKey(dniManager)) {
            List<Deuda> deudasManager = deudasPorManager.get(dniManager);

            if (deuda != null && !deudasManager.contains(deuda)) {
                deudasManager.add(deuda);
                System.out.println("Deuda asignada al manager con DNI " + dniManager);
                return true;
            } else {
                System.out.println("Error: Deuda nula o ya asignada al manager.");
            }
        } else {
            System.out.println("Error: Manager con DNI " + dniManager + " no encontrado.");
        }

        return false;
    }

    public List<Deuda> listarDeudasMayoresA(double parametro) {
        List<Deuda> deudasMayoresAParametro = new ArrayList<>();

        for (List<Deuda> deudasManager : deudasPorManager.values()) {
            for (Deuda deuda : deudasManager) {
                if (deuda.getImporte() > parametro) {
                    deudasMayoresAParametro.add(deuda);
                }
            }
        }

        return deudasMayoresAParametro;
    }

    public Map<String, Double> informePorManager() {
        Map<String, Double> informe = new HashMap<>();

        for (String dniManager : deudasPorManager.keySet()) {
            List<Deuda> deudasManager = deudasPorManager.get(dniManager);
            double totalAsignado = deudasManager.stream().mapToDouble(Deuda::getImporte).sum();
            informe.put(dniManager, totalAsignado);
        }

        return informe;
    }

    public List<Deuda> consultarDeudaPorDniManager(String dniManager) {
        List<Deuda> deudasManager = new ArrayList<>();

        if (managers.containsKey(dniManager)) {
            deudasManager = deudasPorManager.get(dniManager);
        } else {
            System.out.println("Error: Manager con DNI " + dniManager + " no encontrado.");
        }

        return deudasManager;
    }

  
}

public class final2 {
	public static void main(String[] args) {
        // Crear estudio de cobranzas
        EstudioCobranzas estudio = new EstudioCobranzas("Better Charge");

        // Cargar managers
        Manager manager1 = new Manager("12345678", "Juan Perez", "M001");
        Manager manager2 = new Manager("87654321", "Maria Rodriguez", "M002");

        estudio.cargarManager(manager1);
        estudio.cargarManager(manager2);

        // Asignar deudas a los managers
        Deuda deuda1 = new Deuda("D001", 1500.0, new Date());
        Deuda deuda2 = new Deuda("D002", 2000.0, new Date());
        Deuda deuda3 = new Deuda("D003", 1200.0, new Date());

        estudio.asignarDeuda(manager1.getDni(), deuda1);
        estudio.asignarDeuda(manager1.getDni(), deuda2);
        estudio.asignarDeuda(manager2.getDni(), deuda3);

        // Listar deudas mayores a un parámetro
        double parametro = 1500.0;
        System.out.println("Deudas mayores a " + parametro + ": " + estudio.listarDeudasMayoresA(parametro));

        // Generar informe por manager
        Map<String, Double> informe = estudio.informePorManager();
        System.out.println("Informe por manager:");
        for (Map.Entry<String, Double> entry : informe.entrySet()) {
            System.out.println("Manager con DNI " + entry.getKey() + ": Total asignado " + entry.getValue());
        }

        // Consultar deuda por DNI de manager
        String dniManagerConsulta = "12345678";
        List<Deuda> deudasConsulta = estudio.consultarDeudaPorDniManager(dniManagerConsulta);
        System.out.println("Deudas asignadas al manager con DNI " + dniManagerConsulta + ": " + deudasConsulta);
    }
}