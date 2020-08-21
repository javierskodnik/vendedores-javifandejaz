package ar.edu.unahur.obj2.vendedores

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class VendedorTest : DescribeSpec({
  val misiones = Provincia(1300000)
  val sanIgnacio = Ciudad(misiones)

  val buenosAires = Provincia(16660000)
  val hurlingham = Ciudad(buenosAires)
  val moron = Ciudad(buenosAires)

  val mendoza = Provincia(2500000)
  val sanRafael = Ciudad(mendoza)

  val santaFe = Provincia(4000000)
  val rosario = Ciudad(santaFe)
  val sunchales = Ciudad(santaFe)
  val rufino = Ciudad(santaFe)

  val salta = Provincia(2500000)
  val tartagal = Ciudad(salta)

  val certificacionA = Certificacion(true, 15)
  val certificacionB = Certificacion(false, 10)
  val certificacionC = Certificacion(false, 5)


  describe("Vendedor fijo") {
    val obera = Ciudad(misiones)
    val vendedorFijo = VendedorFijo(obera)

    vendedorFijo.agregarCertificacion(certificacionB)
    vendedorFijo.agregarCertificacion(certificacionB)
    vendedorFijo.agregarCertificacion(certificacionB)


    describe("puedeTrabajarEn") {
      it("su ciudad de origen") {
        vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
      }
      it("otra ciudad") {
        vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }
    describe("Es Versaltil"){
      vendedorFijo.esVersatil().shouldBeFalse()
    }
    describe("Es Firme"){
      vendedorFijo.esFirme().shouldBeTrue()
    }
    describe("Es Influyente"){
      vendedorFijo.esInfluyente().shouldBeFalse()
    }

  }

  describe("Viajante") {
    val cordoba = Provincia(2000000)
    val villaDolores = Ciudad(cordoba)
    val viajante = Viajante(listOf(misiones, mendoza, santaFe, salta))

    viajante.agregarCertificacion(certificacionA)
    viajante.agregarCertificacion(certificacionB)
    viajante.agregarCertificacion(certificacionC)

    describe("puedeTrabajarEn") {
      it("una ciudad que pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
      }
      it("una ciudad que no pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(villaDolores).shouldBeFalse()
      }
    }
    describe("Es Versaltil"){
      viajante.esVersatil().shouldBeTrue()
    }
    describe("Es Firme"){
      viajante.esFirme().shouldBeTrue()
    }
    describe("Es Influyente"){
      viajante.esInfluyente().shouldBeTrue()
    }
  }

  describe("Comercio Corresponsal"){

    val comercioCorresponsal1 = ComercioCorresponsal(listOf(hurlingham,rufino, sunchales))

    comercioCorresponsal1.agregarCertificacion(certificacionA)
    comercioCorresponsal1.agregarCertificacion(certificacionA)
    comercioCorresponsal1.agregarCertificacion(certificacionA)

    describe("puedeTrabajarEn") {
      it("una ciudad que tiene sucursal") {
        comercioCorresponsal1.puedeTrabajarEn(hurlingham).shouldBeTrue()
      }
      it("una ciudad que no tiene sucursal") {
        comercioCorresponsal1.puedeTrabajarEn(moron).shouldBeFalse()
      }
    }
    describe("Es Versaltil"){
      comercioCorresponsal1.esVersatil().shouldBeFalse()
    }
    describe("Es Firme"){
      comercioCorresponsal1.esFirme().shouldBeTrue()
    }
    describe("Influyente"){
      describe("No es"){
        comercioCorresponsal1.esInfluyente().shouldBeFalse()
      }
      describe("Si es"){
        val comercioCorresponsal2 = ComercioCorresponsal(listOf(rosario, tartagal, sanRafael))
        comercioCorresponsal2.esInfluyente().shouldBeTrue()
      }
    }
  }

})
